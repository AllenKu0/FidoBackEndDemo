package com.example.springboot.controller;

import com.example.springboot.request.TeachRequest;
import com.example.springboot.service.TeachService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:8080","http://123.241.245.130:8080"})
public class TeachController {

    @Autowired
    TeachService teachService;

    @PostMapping("/api/teach/post")
    @ApiOperation(value ="新增授課")
    public ResponseEntity<?> addTech(@RequestBody TeachRequest teachRequest){
        try {
            teachService.addTech(teachRequest);
            return new ResponseEntity<TeachRequest>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Error>(HttpStatus.CONFLICT);
        }
    }
}
