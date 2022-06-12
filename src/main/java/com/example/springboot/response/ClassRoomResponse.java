package com.example.springboot.response;



public class ClassRoomResponse {

    private Long classId;


    private String className;

    private String classLocation;

    private String classRoomPhoneNumber;

    public String getClassRoomPhoneNumber() {
        return classRoomPhoneNumber;
    }

    public void setClassRoomPhoneNumber(String classRoomPhoneNumber) {
        this.classRoomPhoneNumber = classRoomPhoneNumber;
    }

    public String getClassLocation() {
        return classLocation;
    }

    public void setClassLocation(String classLocation) {
        this.classLocation = classLocation;
    }

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

    public ClassRoomResponse(Long classId, String className, String classLocation, String classRoomPhoneNumber) {
        this.classId = classId;
        this.className = className;
        this.classLocation = classLocation;
        this.classRoomPhoneNumber = classRoomPhoneNumber;
    }

}
