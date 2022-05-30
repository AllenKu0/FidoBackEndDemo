package com.example.springboot.response;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TeacherResponse {
    private Long teacherId;

    private String teacherName;
    private String LessonName;
    private String classRoomName;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getLessonName() {
        return LessonName;
    }

    public void setLessonName(String lessonName) {
        LessonName = lessonName;
    }

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }

    public TeacherResponse(Long teacherId, String teacherName, String lessonName, String classRoomName) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        LessonName = lessonName;
        this.classRoomName = classRoomName;
    }
}
