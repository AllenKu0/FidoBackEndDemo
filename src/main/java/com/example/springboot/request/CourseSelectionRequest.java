package com.example.springboot.request;

import com.example.springboot.entity.Lesson;
import com.example.springboot.entity.User;

import javax.persistence.*;

public class CourseSelectionRequest {

    private Long lesson_id=0L;

    public CourseSelectionRequest(Long lesson_id, Long user_id) {
        this.lesson_id = lesson_id;
        this.user_id = user_id;
    }

    private Long user_id=0L;

    public Long getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(Long lesson_id) {
        this.lesson_id = lesson_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
