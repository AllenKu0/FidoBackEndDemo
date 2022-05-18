package com.example.springboot.controller;

import com.example.springboot.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    @GetMapping("/getUser")
    public ResponseEntity<?> getUser(){
        return ResponseEntity.ok("12345");
    }
    @PostMapping("/postUser")
    public ResponseEntity<?> postUser(@RequestBody User user){
        return ResponseEntity.ok(user);
    }
}
