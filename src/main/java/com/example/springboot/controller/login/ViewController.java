package com.example.springboot.controller.login;

import com.example.springboot.request.FinishAuthRequest;
import com.example.springboot.service.UserService;
import com.yubico.webauthn.RelyingParty;
import com.yubico.webauthn.exception.RegistrationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;

@Controller
public class ViewController {
    @Autowired
    private UserService userService;
    private RelyingParty relyingParty;


    ViewController(RelyingParty relyingPary) {
        this.relyingParty = relyingPary;
    }

    @GetMapping("/home")
    @ApiIgnore
    public String welcome() {
        return "home";
    }

    @GetMapping("/register")
    @ApiIgnore
    public String registerUser(Model model) {
        return "register";
    }

    @GetMapping("/login")
    @ApiIgnore
    public String loginPage() {
        return "login";
    }



}
