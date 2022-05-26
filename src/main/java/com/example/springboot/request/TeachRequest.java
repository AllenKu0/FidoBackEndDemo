package com.example.springboot.request;

import com.example.springboot.entity.Lesson;
import com.example.springboot.entity.Teacher;

public class TeachRequest {
    Long teacherId;
    Long lessonId;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public TeachRequest(Long teacherId, Long lessonId) {
        this.teacherId = teacherId;
        this.lessonId = lessonId;
    }
}
