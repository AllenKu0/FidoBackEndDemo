package com.example.springboot.request;

public class BelongPostRequest {
    private Long office_id ;

    public Long getOffice_id() {
        return office_id;
    }

    public void setOffice_id(Long office_id) {
        this.office_id = office_id;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    private Long teacher_id;

    public BelongPostRequest(Long office_id, Long teacher_id) {
        this.office_id = office_id;
        this.teacher_id = teacher_id;
    }
}
