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
import com.yubico.webauthn.*;
import com.yubico.webauthn.data.*;
import com.yubico.webauthn.exception.AssertionFailedException;
import com.yubico.webauthn.exception.RegistrationFailedException;
import io.swagger.annotations.ApiOperation;
import jdk.internal.icu.text.NormalizerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
//@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    private RelyingParty relyingParty;


    UserController(RelyingParty relyingPary) {
        this.relyingParty = relyingPary;
    }
    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody @Valid UserRegisterRequest userRegister) {
        try {
            User user=userService.register(userRegister);
//            , session
            return newAuthRegistration(user);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException("Save failed, the user name already exist.");
        }





    }
    @PostMapping("/registerauth")
    @ResponseBody
    public String newAuthRegistration(
            @RequestBody User user
    ){
        return userService.newAuthRegistration(user,relyingParty);
    }

    @PostMapping("/finishauth")
    @ResponseBody
    public ModelAndView finishRegisration(
            @RequestBody FinishAuthRequest request
    ) {
        try {
            userService.finishAuth(request, relyingParty);
            return new ModelAndView("redirect:/login", HttpStatus.SEE_OTHER);
        } catch (RegistrationFailedException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Registration failed.", e);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to save credenital, please try again!", e);
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public String startLogin(
            StartLoginRequest loginRequest
    ) {
        return userService.startLogin(loginRequest,relyingParty);
    }


    @PostMapping("/welcome")
    public String finishLogin(
          @RequestBody WelcomRequest request,
          Model model
    ) {
       ;
        if (userService.finishLogin(request,relyingParty).isSuccess()) {
            model.addAttribute("username", request.getUsername());
            return "welcome";
        } else {
            return "index";
        }

    }



}
