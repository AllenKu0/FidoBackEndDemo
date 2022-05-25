package com.example.springboot.controller;

import com.example.springboot.model.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    @GetMapping("/test/getUser")
    @ApiOperation(value = "取得使用者")
    public ResponseEntity<?> getUser(){
        return ResponseEntity.ok("12345");
    }
    @PostMapping("/postUser")
    @ApiOperation(value = "新增使用者")
    public ResponseEntity<?> postUser(@RequestBody User user){
        return ResponseEntity.ok(user);
    }
}
