package com.example.springboot.request;

import com.example.springboot.entity.Lesson;
import com.example.springboot.entity.User;

import javax.persistence.*;

public class CourseSelectionRequest {

    private Long lesson_id=0L;

    public CourseSelectionRequest(Long lesson_id, String user_name) {
        this.lesson_id = lesson_id;
        this.user_name = user_name;
    }

    private String user_name="";

    public Long getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(Long lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
