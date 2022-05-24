package com.example.springboot.controller;

import com.example.springboot.constant.SecurityConstants;
import com.example.springboot.entity.User;
import com.example.springboot.request.LessonRequest;
import com.example.springboot.request.LoginRequest;
import com.example.springboot.response.ListLessonResponse;
import com.example.springboot.service.LessonService;
import com.sun.net.httpserver.Authenticator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lesson")
public class LessonController {

    @Autowired
    LessonService lessonService;


    @PostMapping("/lesson")
    public void postLesson(@RequestBody LessonRequest request){
            lessonService.saveLesson(request);
    }

    @GetMapping("/list")
    @ApiOperation(value = "登入")
    public ResponseEntity<ListLessonResponse> login() {
        ListLessonResponse response=lessonService.getAllLesson();
        if (response.getLessonResponseList().size()>0){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody LessonRequest lessonRequest){
        try {
            lessonService.deleteLesson(lessonRequest);
            return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Error>(HttpStatus.CONFLICT);
        }

    }
}
