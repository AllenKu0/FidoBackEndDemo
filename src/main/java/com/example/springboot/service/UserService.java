package com.example.springboot.service;

import com.example.springboot.entity.Authenticator;
import com.example.springboot.entity.User;
import com.example.springboot.exception.AlreadyExistsException;
import com.example.springboot.repository.AuthenticatorRepository;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.request.FinishAuthRequest;
import com.example.springboot.request.UserLoginRequest;
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

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Autowired
//    private AuthenticatorRepository authenticatorRepository;
//
//
//    private final Cache<ByteArray, PublicKeyCredentialCreationOptions> registrationCache;
//    private final Cache<String,  AssertionRequest> loginCache;
//    public UserService() {
//        this.loginCache = Caffeine.newBuilder().maximumSize(1000)
//                .expireAfterAccess(5, TimeUnit.MINUTES).build();
//        this.registrationCache = Caffeine.newBuilder().maximumSize(1000)
//                .expireAfterAccess(5, TimeUnit.MINUTES).build();
//    }

    public UserService(){

    }
    @Transactional(rollbackFor = Exception.class)
    public void register(UserRegisterRequest userRegisterRequest) {
        Optional<User> userOptional = userRepository.findByAccount(userRegisterRequest.getAccount());
        if (userOptional.isPresent()) {
            throw new AlreadyExistsException("Save failed, the user name already exist.");
        }
        User user = new User(userRegisterRequest);
        userRepository.save(user);
    }

    public Optional<User> findByEmail(String userName) {
        return userRepository.findByEmail(userName);
    }

    public String startLogin(UserLoginRequest userLoginRequest) {
        Optional<User> userOptional = userRepository.findByAccount(userLoginRequest.getAccount());
        if(userOptional.isPresent()){
            if(userOptional.get().getPassword().equals(userLoginRequest.getPassword())){
//                System.out.println(userOptional.get().getPassword());
//                System.out.println(userLoginRequest.getPassword());
                return "Login Suesses";
            }else{
                throw new AlreadyExistsException("login failed, the password was wrong.");
            }
        }else{
            throw new AlreadyExistsException("login failed, the user name dosen't exist.");
        }
    }
}
