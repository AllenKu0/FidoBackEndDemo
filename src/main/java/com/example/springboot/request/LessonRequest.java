package com.example.springboot.request;

import javax.persistence.Column;

public class LessonRequest {
    public LessonRequest(String lesson_name, Integer lesson_credit) {
        this.lesson_name = lesson_name;
        this.lesson_credit = lesson_credit;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public Integer getLesson_credit() {
        return lesson_credit;
    }

    public void setLesson_credit(Integer lesson_credit) {
        this.lesson_credit = lesson_credit;
    }

    private String lesson_name;
    private Integer lesson_credit;
}
