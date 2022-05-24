package com.example.springboot.request;

import com.example.springboot.entity.Lesson;

public class CourseSelectionGetAllRequest {
    public CourseSelectionGetAllRequest(Long choose_id, Lesson lesson) {
        this.choose_id = choose_id;
        this.lesson = lesson;
    }

    private Long choose_id;

    private Lesson lesson;

    public Long getChoose_id() {
        return choose_id;
    }

    public void setChoose_id(Long choose_id) {
        this.choose_id = choose_id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
