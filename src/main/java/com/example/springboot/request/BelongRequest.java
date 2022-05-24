package com.example.springboot.request;

public class BelongRequest {
//    private Long classRoom_id ;

    public BelongRequest() {
    }

    public BelongRequest(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    private Long teacher_id;
}
