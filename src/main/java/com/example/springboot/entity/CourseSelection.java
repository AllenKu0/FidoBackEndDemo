package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor
public class CourseSelection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chooseId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "pk_lesson", referencedColumnName = "lessonId")
    private Lesson lesson;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "pk_user", referencedColumnName = "id")
    private User user;

    public CourseSelection(Lesson lesson, User user) {
        this.lesson = lesson;
        this.user = user;
    }

    public Long getChooseId() {
        return chooseId;
    }

    public void setChooseId(Long chooseId) {
        this.chooseId = chooseId;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
