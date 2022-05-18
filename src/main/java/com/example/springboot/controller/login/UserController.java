package com.example.springboot.controller.login;

import com.example.springboot.request.UserRegisterRequest;
import com.example.springboot.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public ResponseEntity<Void> register(@RequestBody @Valid UserRegisterRequest userRegister) {
        userService.register(userRegister);
        return ResponseEntity.ok().build();
    }
}
