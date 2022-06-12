package com.example.springboot.controller;

import com.example.springboot.entity.Belong;
import com.example.springboot.repository.BelongRepository;
import com.example.springboot.request.BelongPostRequest;
import com.example.springboot.request.BelongRequest;
import com.example.springboot.request.TeacherRequest;
import com.example.springboot.service.BelongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/belong")
public class BelongController {
    @Autowired
    BelongService belongService;

    @GetMapping("/get")
    public ResponseEntity<? extends Object> getClassRoomByTeacher(@RequestParam String teacherName){
        try {
            return new ResponseEntity<>(belongService.getClassRoomByTeacherId(teacherName),HttpStatus.OK);
        }catch (Exception e){
            System.out.print(e);
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> postBelong(@RequestBody BelongPostRequest belongPostRequest){
        try {
            belongService.saveBelong(belongPostRequest);
            return new ResponseEntity<>("New Belong Successes",HttpStatus.OK);
        }catch (Exception e){
            System.out.print(e);
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteBelong(@RequestBody BelongRequest belongRequest){
        try {
            belongService.deleteBelong(belongRequest);
            return new ResponseEntity<>("Delete Belong Successes",HttpStatus.OK);
        }catch (Exception e){
            System.out.print(e);
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }
}

