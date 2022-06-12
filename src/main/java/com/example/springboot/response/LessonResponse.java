package com.example.springboot.response;

public class LessonResponse {
    private int lessonId = 0;
    private String lessonName = "";
    private Integer lessonCredit = 0;

    private String lessonStatus="";

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public LessonResponse(int lessonId, String lessonName, Integer lessonCredit, String lessonStatus) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.lessonCredit = lessonCredit;
        this.lessonStatus = lessonStatus;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Integer getLessonCredit() {
        return lessonCredit;
    }

    public void setLessonCredit(Integer lessonCredit) {
        this.lessonCredit = lessonCredit;
    }

    public String getLessonStatus() {
        return lessonStatus;
    }

    public void setLessonStatus(String lessonStatus) {
        this.lessonStatus = lessonStatus;
    }
}

