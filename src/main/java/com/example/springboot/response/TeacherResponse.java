package com.example.springboot.response;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TeacherResponse {
    private Long teacher_id;

    private String teacher_name;

    private String classRoomName;

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public TeacherResponse(Long teacher_id, String teacher_name, String classRoomName) {
        this.teacher_id = teacher_id;
        this.teacher_name = teacher_name;
        this.classRoomName = classRoomName;
    }
}
