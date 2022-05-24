package com.example.springboot.response;

import com.example.springboot.entity.Lesson;

public class CourseSelectionResponse {

    private Long lessonId;
    private String lessonName;

    public CourseSelectionResponse(Long lessonId, String lessonName) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }
}
