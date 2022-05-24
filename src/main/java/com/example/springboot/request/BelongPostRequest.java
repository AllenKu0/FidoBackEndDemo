package com.example.springboot.request;

public class BelongPostRequest {
    private Long classRoom_id ;

    public Long getClassRoom_id() {
        return classRoom_id;
    }

    public void setClassRoom_id(Long classRoom_id) {
        this.classRoom_id = classRoom_id;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    private Long teacher_id;

    public BelongPostRequest(Long classRoom_id, Long teacher_id) {
        this.classRoom_id = classRoom_id;
        this.teacher_id = teacher_id;
    }
}
