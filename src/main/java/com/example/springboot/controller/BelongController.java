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
    public ResponseEntity<? extends Object> getClassRoomByTeacher(@RequestBody BelongRequest belongRequest){

        try {
            return new ResponseEntity<>(belongService.getClassRoomByTeacherId(belongRequest),HttpStatus.OK);
        }catch (Exception e){
            System.out.print(e);
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/post")
    public void postBelong(@RequestBody BelongPostRequest belongPostRequest){
        try {
            belongService.saveBelong(belongPostRequest);
        }catch (Exception e){
            System.out.print(e);
        }
    }

//    @PostMapping("/delete")
//    public void deleteBelong(@RequestBody BelongRequest belongRequest){
//        try {
//            belongService.deleteBelong(belongRequest);
//        }catch (Exception e){
//            System.out.print(e);
//        }
//    }
}

