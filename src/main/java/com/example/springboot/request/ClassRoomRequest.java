package com.example.springboot.request;

public class ClassRoomRequest {
    public String getClassName() {
        return className;
    }

    public ClassRoomRequest() {
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ClassRoomRequest(String className) {
        this.className = className;
    }

    private String className;
}
