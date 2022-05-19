package com.example.springboot.service;

import com.example.springboot.entity.Authenticator;
import com.example.springboot.entity.User;
import com.example.springboot.exception.AlreadyExistsException;
import com.example.springboot.repository.AuthenticatorRepository;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.request.FinishAuthRequest;
import com.example.springboot.request.StartLoginRequest;
import com.example.springboot.request.UserRegisterRequest;
import com.example.springboot.request.WelcomRequest;
import com.example.springboot.utitlity.UserMapper;
import com.example.springboot.utitlity.Utility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.yubico.webauthn.*;
import com.yubico.webauthn.data.*;
import com.yubico.webauthn.exception.AssertionFailedException;
import com.yubico.webauthn.exception.RegistrationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticatorRepository authenticatorRepository;


    private final Cache<String, PublicKeyCredentialCreationOptions> registrationCache;
    private final Cache<String,  AssertionRequest> loginCache;
    public UserService() {
        this.loginCache = Caffeine.newBuilder().maximumSize(1000)
                .expireAfterAccess(5, TimeUnit.MINUTES).build();
        this.registrationCache = Caffeine.newBuilder().maximumSize(1000)
                .expireAfterAccess(5, TimeUnit.MINUTES).build();
    }

    @Transactional(rollbackFor = Exception.class)
    public User register(UserRegisterRequest dto) {
        Optional<User> userOptional = findByEmail(dto.getUsername());
        if (userOptional.isPresent()) {
            throw new AlreadyExistsException("Save failed, the user name already exist.");
        }

        UserIdentity userIdentity = UserIdentity.builder()
                .name(dto.getUsername())
                .displayName(dto.getDisplayName())
                .id(Utility.generateRandom(32))
                .build();
        User user = new User(userIdentity);
        String cryptPassword = bCryptPasswordEncoder.encode(dto.getPassword());
        user.setPassword(cryptPassword);
        userRepository.save(user);
        return user;

    }

    public Optional<User> findByEmail(String userName) {
        return userRepository.findByEmail(userName);
    }


    public String newAuthRegistration(
            User user,
            RelyingParty relyingParty) {
        Optional<User> existingUser = userRepository.findByHandle(user.getHandle());
        if (existingUser.isPresent()) {
            UserIdentity userIdentity = user.toUserIdentity();
            StartRegistrationOptions registrationOptions = StartRegistrationOptions.builder()
                    .user(userIdentity)
                    .build();

//            HttpSession session

//            registrationCache.setAttribute(userIdentity.getDisplayName(), registration);
            try {
                PublicKeyCredentialCreationOptions registration = relyingParty.startRegistration(registrationOptions);
                this.registrationCache.put(userIdentity.getDisplayName(), registration);
                return registration.toCredentialsCreateJson();
            } catch (JsonProcessingException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error processing JSON.", e);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User " + user.getEmail() + " does not exist. Please register.");
        }
    }


    public String startLogin(StartLoginRequest username, RelyingParty relyingParty){
        AssertionRequest request = relyingParty.startAssertion(StartAssertionOptions.builder()
                .username(username.getUsername())
                .build());
        try {
            loginCache.put(username.getUsername(),request);
            return request.toCredentialsGetJson();
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public void finishAuth(FinishAuthRequest request, RelyingParty relyingParty) throws IOException, RegistrationFailedException {
        Optional<User> user = findByEmail(request.getUsername());
        if (user.isPresent()) {
            PublicKeyCredentialCreationOptions requestOptions = (PublicKeyCredentialCreationOptions) registrationCache.getIfPresent(user.get().getEmail());
            this.registrationCache.invalidate(user.get().getEmail());

            if (requestOptions != null) {
                PublicKeyCredential<AuthenticatorAttestationResponse, ClientRegistrationExtensionOutputs> pkc =
                        PublicKeyCredential.parseRegistrationResponseJson(request.getCredential());
                FinishRegistrationOptions options = FinishRegistrationOptions.builder()
                        .request(requestOptions)
                        .response(pkc)
                        .build();
                RegistrationResult result = relyingParty.finishRegistration(options);
                Authenticator savedAuth = new Authenticator(result, pkc.getResponse(), user.get(), request.getCredname());
                authenticatorRepository.save(savedAuth);
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Cached request expired. Try to register again!");
            }


        }
    }


    public AssertionResult finishLogin(WelcomRequest welcomRequest, RelyingParty relyingParty){
        try {
            PublicKeyCredential<AuthenticatorAssertionResponse, ClientAssertionExtensionOutputs> pkc;
            pkc = PublicKeyCredential.parseAssertionResponseJson(welcomRequest.getCredential());
            AssertionRequest request = (AssertionRequest)loginCache.getIfPresent(welcomRequest.getUsername());
            AssertionResult result = relyingParty.finishAssertion(FinishAssertionOptions.builder()
                    .request(request)
                    .response(pkc)
                    .build());
           return result;
        } catch (IOException e) {
            throw new RuntimeException("Authentication failed", e);
        } catch (AssertionFailedException e) {
            throw new RuntimeException("Authentication failed", e);
        }
    }
}
