package com.example.springboot.request;

public class ClassRoomRequest {


    private String className;

    private String classPhoneNumber;

    private String classLocation;
    public String getClassPhoneNumber() {
        return classPhoneNumber;
    }

    public void setClassPhoneNumber(String classPhoneNumber) {
        this.classPhoneNumber = classPhoneNumber;
    }

    public String getClassLocation() {
        return classLocation;
    }

    public void setClassLocation(String classLocation) {
        this.classLocation = classLocation;
    }


    public String getClassName() {
        return className;
    }


    public void setClassName(String className) {
        this.className = className;
    }


    public ClassRoomRequest(String className, String classPhoneNumber, String classLocation) {
        this.className = className;
        this.classPhoneNumber = classPhoneNumber;
        this.classLocation = classLocation;
    }

    public ClassRoomRequest() {
    }


}
