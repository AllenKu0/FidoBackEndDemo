package com.example.springboot.controller;

import com.example.springboot.request.TeacherRequest;
import com.example.springboot.response.ListLessonResponse;
import com.example.springboot.response.TeacherResponse;
import com.example.springboot.service.TeacherService;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @GetMapping("/get")
    public ResponseEntity<?> getTeacher() {
        List<TeacherResponse> response =teacherService.getAllTeacher();
        if (response.size()>0){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> postTeacher(@RequestBody TeacherRequest teacherRequest){
        try {
            teacherService.saveTeacher(teacherRequest);
            return new ResponseEntity<TeacherRequest>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Error>(HttpStatus.CONFLICT);
        }


    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteTeacher(@RequestBody TeacherRequest teacherRequest){
        try {
            teacherService.deleteTeacher(teacherRequest);
            return new ResponseEntity<TeacherRequest>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Error>(HttpStatus.CONFLICT);
        }

    }
}
