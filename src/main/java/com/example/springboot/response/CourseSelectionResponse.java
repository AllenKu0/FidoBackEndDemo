package com.example.springboot.response;

import com.example.springboot.entity.Lesson;

public class CourseSelectionResponse {

    private Long lessonId;

    public Integer getLessonCredit() {
        return lessonCredit;
    }

    public void setLessonCredit(Integer lessonCredit) {
        this.lessonCredit = lessonCredit;
    }

    private String lessonName;

    private Integer lessonCredit;

    private String lessonStatus;

    public String getLessonStatus() {
        return lessonStatus;
    }

    public void setLessonStatus(String lessonStatus) {
        this.lessonStatus = lessonStatus;
    }

    public CourseSelectionResponse(Long lessonId, String lessonName, Integer lessonCredit, String lessonStatus) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.lessonCredit = lessonCredit;
        this.lessonStatus = lessonStatus;
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
