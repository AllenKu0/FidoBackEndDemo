package com.example.springboot.controller;

import com.example.springboot.request.TeacherRequest;
import com.example.springboot.response.ListLessonResponse;
import com.example.springboot.response.TeacherResponse;
import com.example.springboot.service.TeacherService;
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
    public void postTeacher(@RequestBody TeacherRequest teacherRequest){
        teacherService.saveTeacher(teacherRequest);
    }

    @PostMapping("/delete")
    public void deleteTeacher(@RequestBody TeacherRequest teacherRequest){
        teacherService.deleteTeacher(teacherRequest);
    }
}
