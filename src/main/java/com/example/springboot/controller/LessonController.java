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
@CrossOrigin(origins = {"http://localhost:8080","http://123.241.245.130:8080/"})
@RequestMapping("/api/lesson")
public class LessonController {

    @Autowired
    LessonService lessonService;


    @PostMapping("/lesson")
    @ApiOperation(value = "新增課程")
    public void postLesson(@RequestBody LessonRequest request){
            lessonService.saveLesson(request);
    }

    @GetMapping("/list")
    @ApiOperation(value = "取得課程")
    public ResponseEntity<ListLessonResponse> getAllLesson() {
        ListLessonResponse response=lessonService.getAllLesson();
        if (response.getLessonResponseList().size()>0){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "刪除課程")
    public ResponseEntity<?> delete(@RequestBody LessonRequest lessonRequest){
        try {
            lessonService.deleteLesson(lessonRequest);
            return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Error>(HttpStatus.CONFLICT);
        }
    }
}
