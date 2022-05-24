package com.example.springboot.request;

import com.example.springboot.entity.ClassRoom;

import javax.persistence.*;

public class TeacherRequest {

    private String teacher_name;

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public TeacherRequest() {
    }

    public TeacherRequest(String teacher_name) {
        this.teacher_name = teacher_name;
    }
}
