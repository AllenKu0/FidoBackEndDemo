package com.example.springboot.controller;

import com.example.springboot.request.OfficeRequest;
import com.example.springboot.service.OfficeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/office")
public class OfficeController {

    @Autowired
    OfficeService officeService;

    @PostMapping("/post")
    @ApiOperation(value = "新增辦公室")
    public ResponseEntity<?> postOffice(@RequestBody OfficeRequest officeRequest){
        try {
            officeService.saveOffice(officeRequest);
            return new ResponseEntity<>("New Office Successes", HttpStatus.OK);
        }catch (Exception e){
            System.out.print(e);
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get")
    @ApiOperation(value = "取得辦公室")
    public ResponseEntity<? extends Object> getOffice(){
        try {
            return new ResponseEntity<>(officeService.getAllOffice(), HttpStatus.OK);
        }catch (Exception e){
            System.out.print(e);
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "刪除辦公室")
    public ResponseEntity<? extends Object> deleteClassRoom(@RequestBody OfficeRequest officeRequest){
        try {
            officeService.deleteOffice(officeRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.print(e);
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }

}
