package com.example.springboot.controller.login;

import com.example.springboot.entity.Authenticator;
import com.example.springboot.entity.User;
import com.example.springboot.exception.AlreadyExistsException;
import com.example.springboot.repository.AuthenticatorRepository;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.request.FinishAuthRequest;
import com.example.springboot.request.StartLoginRequest;
import com.example.springboot.request.UserRegisterRequest;
import com.example.springboot.request.WelcomRequest;
import com.example.springboot.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.yubico.webauthn.*;
import com.yubico.webauthn.data.*;
import com.yubico.webauthn.exception.AssertionFailedException;
import com.yubico.webauthn.exception.RegistrationFailedException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    private RelyingParty relyingParty;

    //
    private final Cache<String, PublicKeyCredentialCreationOptions> registrationCache;
    private final Cache<String, AssertionRequest> loginCache;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticatorRepository authenticatorRepository;

    public UserController(RelyingParty relyingPary) {
        this.relyingParty = relyingPary;
        this.loginCache = Caffeine.newBuilder().maximumSize(200000)
                .expireAfterAccess(5, TimeUnit.MINUTES).build();
        this.registrationCache = Caffeine.newBuilder().maximumSize(200000)
                .expireAfterAccess(5, TimeUnit.MINUTES).build();
    }

    @PostMapping("/register")
    @ApiOperation(value = "註冊")
    @ResponseBody
    public String register(@RequestBody @Valid UserRegisterRequest userRegister) {
        try {
            System.out.print("fuck fuck fuck ");
            User user = userService.register(userRegister);
//            , session
            return newAuthRegistration(user);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException("Save failed, the user name already exist.");
        }


    }

    @PostMapping("/registerauth")
    @ApiOperation(value = "註冊選擇驗證方式")
    @ResponseBody
    public String newAuthRegistration(
            @RequestBody User user
    ) {
//        return userService.newAuthRegistration(user,relyingParty);
        Optional<User> existingUser = userRepository.findByHandle(user.getHandle());
        if (existingUser.isPresent()) {
            UserIdentity userIdentity = user.toUserIdentity();
            StartRegistrationOptions registrationOptions = StartRegistrationOptions.builder()
                    .user(userIdentity)
                    .build();

//            HttpSession session

//            registrationCache.setAttribute(userIdentity.getDisplayName(), registration);
            try {
                //會進RegistrationService的getCredentialIdsForUsername
                PublicKeyCredentialCreationOptions registration = relyingParty.startRegistration(registrationOptions);
                this.registrationCache.put(userIdentity.getName(), registration);
                return registration.toCredentialsCreateJson();
            } catch (JsonProcessingException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error processing JSON.", e);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User " + user.getEmail() + " does not exist. Please register.");
        }
    }

    @PostMapping("/finishauth")
    @ApiOperation(value = "驗證取得公鑰")
    @ResponseBody
    public ResponseEntity<Void> finishRegisration(
            @RequestBody FinishAuthRequest request
    ) {
        try {
//            userService.finishAuth(request, relyingParty);
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
            return ResponseEntity.ok().build();
        } catch (RegistrationFailedException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Registration failed.", e);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to save credenital, please try again!", e);
        }
    }

    public Optional<User> findByEmail(String userName) {
        return userRepository.findByEmail(userName);
    }

    @PostMapping("/login")
    @ApiOperation(value = "登入")
    @ResponseBody
    public ResponseEntity<?> startLogin(
            @RequestParam String username
    ) {
        try {
            return new ResponseEntity<>(userService.startLogin(username, relyingParty),HttpStatus.OK);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @PostMapping("/welcome")
    @ApiOperation(value = "完成登入，前往主頁")
    public  ResponseEntity<?> finishLogin(
            @RequestParam String credential,
            @RequestParam String username
    ) {
        try {
            if (userService.finishLogin(credential, username, relyingParty).isSuccess()) {
                return new ResponseEntity<>(userService.startLogin(username, relyingParty),HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            throw new RuntimeException("Authentication failed", e);
        } catch (AssertionFailedException e) {
            throw new RuntimeException("Authentication failed", e);
        }
    }
}
