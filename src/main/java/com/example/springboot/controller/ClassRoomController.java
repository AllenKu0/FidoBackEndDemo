package com.example.springboot.controller;

import com.example.springboot.request.ClassRoomRequest;
import com.example.springboot.service.ClassRoomService;
import io.swagger.annotations.ApiOperation;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:8080","http://123.241.245.130:8080/"})
@RequestMapping("/api/classRoom")
public class ClassRoomController {
    @Autowired
    ClassRoomService classRoomService;

    @PostMapping("/post")
    @ApiOperation(value = "新增教室")
    public void postClassRomm(@RequestBody ClassRoomRequest classRequest){
        classRoomService.saveClassRoom(classRequest);
    }

    @GetMapping("get")
    @ApiOperation(value = "取得教室")
    public ResponseEntity<? extends Object> getClassRoom(){
        try {
            return new ResponseEntity<>(classRoomService.getAllClassRoom(), HttpStatus.OK);
        }catch (Exception e){
            System.out.print(e);
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "刪除教室")
    public ResponseEntity<? extends Object> deleteClassRoom(@RequestBody ClassRoomRequest classRoomRequest){
        try {
            classRoomService.deleteClassRoom(classRoomRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.print(e);
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }
}
