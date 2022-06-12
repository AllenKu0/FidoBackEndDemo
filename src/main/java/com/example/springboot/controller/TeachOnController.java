package com.example.springboot.controller;

import com.example.springboot.entity.TeachOn;
import com.example.springboot.request.TeachOnRequest;
import com.example.springboot.request.TeacherRequest;
import com.example.springboot.response.ClassRoomResponse;
import com.example.springboot.response.TeacherResponse;
import com.example.springboot.service.TeachOnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/TeachOn")
public class TeachOnController {
    @Autowired
    TeachOnService teachOnService;

    @GetMapping("/getClassRoomByLesson")
    public ResponseEntity<?> getClassRoomByLesson(@RequestParam Long lessonId) {
        List<ClassRoomResponse> response =teachOnService.getClassRoomByLesson(lessonId);
        if (response.size()>0){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> postTeachOn(@RequestBody TeachOnRequest teachOnRequest){
        try {
            teachOnService.saveTeachOn(teachOnRequest);
            return new ResponseEntity<>("New TeachOn Successes",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("New TeachOn Fail",HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteTeachOn(@RequestBody TeachOnRequest teachOnRequest){
        try {
            teachOnService.deleteTeachOn(teachOnRequest);
            return new ResponseEntity<>("Delete TeachOn Successes",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Delete TeachOn Fail",HttpStatus.CONFLICT);
        }
    }
}
