package com.example.springboot.controller;

import com.example.springboot.constant.SecurityConstants;
import com.example.springboot.entity.CourseSelection;
import com.example.springboot.entity.User;
import com.example.springboot.request.LessonRequest;
import com.example.springboot.request.LoginRequest;
import com.example.springboot.response.LessonResponse;
import com.example.springboot.service.CourseSelectionService;
import com.example.springboot.service.LessonService;
import com.sun.net.httpserver.Authenticator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/lesson")
public class LessonController {

    @Autowired
    LessonService lessonService;


    @PostMapping("/post")
    public ResponseEntity<?> postLesson(@RequestBody LessonRequest request){
        try {
            lessonService.saveLesson(request);
            return new ResponseEntity<>("New Lesson Successes", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("New Lesson Fail", HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/getNotSelect")
    @ApiOperation(value ="獲取未選課程")
    public ResponseEntity<List<LessonResponse>> getNotSelectLesson(@RequestParam String account) {
        List<LessonResponse> response=lessonService.getNotSelectLesson(account);
        if (response.size()>0){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    @ApiOperation(value ="獲取全部課程")
    public ResponseEntity<List<LessonResponse>> getLesson() {
        List<LessonResponse> response=lessonService.getAllLesson();
        if (response.size()>0){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam String lessonName){
        try {
            lessonService.deleteLesson(lessonName);
            return new ResponseEntity<>("Delete Lesson Successes",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Delete Lesson Fail",HttpStatus.CONFLICT);
        }

    }
}
