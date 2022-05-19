package com.example.springboot.controller.login;

import com.example.springboot.entity.Authenticator;
import com.example.springboot.entity.User;
import com.example.springboot.exception.AlreadyExistsException;
import com.example.springboot.repository.AuthenticatorRepository;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.request.UserRegisterRequest;
import com.example.springboot.service.UserService;
import com.yubico.webauthn.FinishRegistrationOptions;
import com.yubico.webauthn.RegistrationResult;
import com.yubico.webauthn.RelyingParty;
import com.yubico.webauthn.data.*;
import com.yubico.webauthn.exception.RegistrationFailedException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
            @RequestParam User user
    ){
        return userService.newAuthRegistration(user);
    }



//    @PostMapping("/register")
//    @ResponseBody
//    public String newUserRegistration(
//            @RequestParam String username,
//            @RequestParam String display,
//            HttpSession session
//    ) {
//        AppUser existingUser = service.getUserRepo().findByUsername(username);
//        if (existingUser == null) {
//            UserIdentity userIdentity = UserIdentity.builder()
//                    .name(username)
//                    .displayName(display)
//                    .id(Utility.generateRandom(32))
//                    .build();
//            AppUser saveUser = new AppUser(userIdentity);
//            service.getUserRepo().save(saveUser);
//            String response = newAuthRegistration(saveUser, session);
//            return response;
//        } else {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username " + username + " already exists. Choose a new name.");
//        }
//    }
}
