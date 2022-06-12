package com.example.springboot.request;

import com.example.springboot.entity.ClassRoom;

import javax.persistence.*;

public class TeacherRequest {

    private String teacherName;

    private String teacherPhoneNumber;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPhoneNumber() {
        return teacherPhoneNumber;
    }

    public void setTeacherPhoneNumber(String teacherPhoneNumber) {
        this.teacherPhoneNumber = teacherPhoneNumber;
    }

    public TeacherRequest() {
    }

    public TeacherRequest(String teacherName, String teacherPhoneNumber) {
        this.teacherName = teacherName;
        this.teacherPhoneNumber = teacherPhoneNumber;
    }
}
