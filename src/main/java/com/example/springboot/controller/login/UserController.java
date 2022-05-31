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
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Controller
@CrossOrigin(origins = {"http://localhost:8080","http://123.241.245.130:8080/"})
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
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User " + user.getUserName() + " does not exist. Please register.");
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
            Optional<User> user = findByUserName(request.getUsername());
            System.out.println("finishRegisration 0000");
            if (user.isPresent()) {
                PublicKeyCredentialCreationOptions requestOptions = (PublicKeyCredentialCreationOptions) registrationCache.getIfPresent(user.get().getUserName());
                this.registrationCache.invalidate(user.get().getUserName());
                System.out.println("finishRegisration 1111");
                if (requestOptions != null) {
                    PublicKeyCredential<AuthenticatorAttestationResponse, ClientRegistrationExtensionOutputs> pkc =
                            PublicKeyCredential.parseRegistrationResponseJson(request.getCredential());
                    FinishRegistrationOptions options = FinishRegistrationOptions.builder()
                            .request(requestOptions)
                            .response(pkc)
                            .build();
                    System.out.println("finishRegisration 2222");
                    RegistrationResult result = relyingParty.finishRegistration(options);
                    System.out.println("finishRegisration 33333");
                    Authenticator savedAuth = new Authenticator(result, pkc.getResponse(), user.get(), request.getCredname());
                    System.out.println("finishRegisration 44444");
                    authenticatorRepository.save(savedAuth);
                } else {
                    System.out.println("finishRegisration 5555");
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Cached request expired. Try to register again!");
                }
            }
            return ResponseEntity.ok().build();
        } catch (RegistrationFailedException e) {
            System.out.println("finishRegisration 6666"+e);
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Registration failed.", e);
        } catch (IOException e) {
            System.out.println("finishRegisration 7777"+e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to save credenital, please try again!", e);
        }
    }

    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
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

    @GetMapping("/get")
    public ResponseEntity<?> getAllUser() {
        try {
            return new ResponseEntity<>(userService.findAllUser(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("delete")
    @ApiOperation(value = "刪除使用者")
    @ResponseBody
    public ResponseEntity<?> deleteUser(
            @RequestBody UserDeleteRequest userDeleteRequest
    ) {
        try {
            userService.deleteUser(userDeleteRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
