package com.example.springboot.controller;

import com.example.springboot.repository.CourseSelectionRepository;
import com.example.springboot.request.CourseSelectionRequest;
import com.example.springboot.request.CourseSelectionUserIdRequest;
import com.example.springboot.service.CourseSelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
public class CourseSelectionController {
    @Autowired
    CourseSelectionService courseSelectionService;

    @PostMapping("/select")
    public ResponseEntity<?> courseSelect(@RequestBody CourseSelectionRequest courseSelectionRequest){
       try {
           courseSelectionService.saveCourse(courseSelectionRequest);
           return new ResponseEntity<>(HttpStatus.OK);
       }catch (Exception e){
           System.out.print(e);
           return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
       }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> courseDelete(@RequestBody CourseSelectionRequest courseSelectionRequest){
        try {
            courseSelectionService.deleteCourse(courseSelectionRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.print(e);
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getByUser")
    public ResponseEntity<?> courseGetByUser(@RequestBody CourseSelectionUserIdRequest courseSelectionUserIdRequest){
        System.out.print("aaaaaaa");
        try {
            return new ResponseEntity<>(courseSelectionService.getAllLesson(courseSelectionUserIdRequest), HttpStatus.OK);
        }catch (Exception e){
            System.out.print(e);
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }

}
