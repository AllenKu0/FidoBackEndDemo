package com.example.springboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Lesson {
    public Lesson(Long lesson_id) {
        this.lesson_id = lesson_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lesson_id;

    @Column(name = "lessonName", columnDefinition = "varchar(68)", nullable = false)
    private String lesson_name;

    public Long getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(Long lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public Integer getLesson_credit() {
        return lesson_credit;
    }

    public void setLesson_credit(Integer lesson_credit) {
        this.lesson_credit = lesson_credit;
    }

    @Column(name = "lessonCredit",  nullable = false)
    private Integer lesson_credit;

    public Lesson(String lesson_name, Integer lesson_credit) {
        this.lesson_name = lesson_name;
        this.lesson_credit = lesson_credit;
    }
}
