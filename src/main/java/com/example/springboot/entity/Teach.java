package com.example.springboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Teach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teachId;

    @OneToOne
    @JoinColumn(name = "pk_teacher", referencedColumnName = "teacherId")
    Teacher teacher;

    @OneToOne
    @JoinColumn(name = "pk_lesson", referencedColumnName = "lessonId")
    Lesson lesson;

    public Long getTeachId() {
        return teachId;
    }

    public Teach(Teacher teacher, Lesson lesson) {
        this.teacher = teacher;
        this.lesson = lesson;
    }

    public void setTeachId(Long teachId) {
        this.teachId = teachId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
