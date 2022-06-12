package com.example.springboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class TeachOn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teachOnId;

    @ManyToOne
    @JoinColumn(name = "pk_classRoom", referencedColumnName = "classId")
    ClassRoom classRoom;

    public TeachOn(ClassRoom classRoom, Lesson lesson) {
        this.classRoom = classRoom;
        this.lesson = lesson;
    }

    @OneToOne
    @JoinColumn(name = "pk_lesson", referencedColumnName = "lessonId")
    Lesson lesson;

    public Long getTeachOnId() {
        return teachOnId;
    }

    public void setTeachOnId(Long teachOnId) {
        this.teachOnId = teachOnId;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
