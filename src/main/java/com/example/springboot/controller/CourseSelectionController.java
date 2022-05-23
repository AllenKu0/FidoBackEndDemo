package com.example.springboot.controller;

import com.example.springboot.repository.CourseSelectionRepository;
import com.example.springboot.request.CourseSelectionRequest;
import com.example.springboot.service.CourseSelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course")
public class CourseSelectionController {
    @Autowired
    CourseSelectionService courseSelectionService;

    @PostMapping("/select")
    public void courseSelect(@RequestBody CourseSelectionRequest courseSelectionRequest){
       try {
           courseSelectionService.saveCourse(courseSelectionRequest);
       }catch (Exception e){
           System.out.print(e);
       }
    }
}
