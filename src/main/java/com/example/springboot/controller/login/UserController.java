package com.example.springboot.controller.login;

import com.example.springboot.entity.Authenticator;
import com.example.springboot.entity.User;
import com.example.springboot.exception.AlreadyExistsException;
import com.example.springboot.repository.AuthenticatorRepository;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.request.*;
import com.example.springboot.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.yubico.webauthn.*;
import com.yubico.webauthn.data.*;
import com.yubico.webauthn.exception.AssertionFailedException;
import com.yubico.webauthn.exception.RegistrationFailedException;
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
//    private final Cache<String, PublicKeyCredentialCreationOptions> registrationCache;
//    private final Cache<String, AssertionRequest> loginCache;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticatorRepository authenticatorRepository;

//    public UserController(RelyingParty relyingPary) {
//        this.relyingParty = relyingPary;
//        this.loginCache = Caffeine.newBuilder().maximumSize(200000)
//                .expireAfterAccess(5, TimeUnit.MINUTES).build();
//        this.registrationCache = Caffeine.newBuilder().maximumSize(200000)
//                .expireAfterAccess(5, TimeUnit.MINUTES).build();
//    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<? extends Object> register(@RequestBody @Valid UserRegisterRequest userRegister) {
        try {
            System.out.print("register");
            userService.register(userRegister);
            return new ResponseEntity<>("Register Successes",HttpStatus.OK);
        } catch (Exception e) {
            System.out.print(e);
            return new ResponseEntity<>("Save failed, the user name already exist.",HttpStatus.CONFLICT);
        }
    }

    public Optional<User> findByEmail(String userName) {
        return userRepository.findByEmail(userName);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> startLogin(@RequestBody UserLoginRequest userLoginRequest) {
        try {
            return new ResponseEntity<>(userService.startLogin(userLoginRequest), HttpStatus.OK);
        } catch (Exception e) {
            System.out.print(e);
            throw new AlreadyExistsException("login failed");
        }
    }


//    @PostMapping("/welcome")
//    public String finishLogin(
//            @RequestParam String credential,
//            @RequestParam String username,
//            Model model
//    ) {
//        ;
//        try {
//
//            if (userService.finishLogin(credential, username, relyingParty).isSuccess()) {
//                model.addAttribute("username", username);
//                return "welcome";
//            } else {
//                return "index";
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Authentication failed", e);
//        } catch (AssertionFailedException e) {
//            throw new RuntimeException("Authentication failed", e);
//        }
//
//    }


}
