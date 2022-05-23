package com.example.springboot.request;

public class CourseSelectionUserIdRequest {

    public CourseSelectionUserIdRequest(Long user_id) {
        this.user_id = user_id;
    }

    private Long user_id=0L;


    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
