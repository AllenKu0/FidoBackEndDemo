package com.example.springboot.request;

public class TeachOnRequest {
    Long lessonId;
    Long classRoomId;

    public TeachOnRequest() {
    }

    public TeachOnRequest(Long lessonId, Long classRoomId) {
        this.lessonId = lessonId;
        this.classRoomId = classRoomId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Long getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(Long classRoomId) {
        this.classRoomId = classRoomId;
    }
}
