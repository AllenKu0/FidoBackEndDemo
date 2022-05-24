package com.example.springboot.response;



public class ClassRoomResponse {

    private Long classId;


    private String className;

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ClassRoomResponse(Long classId, String className) {
        this.classId = classId;
        this.className = className;
    }
}
