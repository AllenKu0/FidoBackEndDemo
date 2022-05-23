package com.example.springboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lesson_id;

    @Column(name = "lessonName", columnDefinition = "varchar(68)", nullable = false)
    private String lesson_name;

    @Column(name = "lessonCredit",  nullable = false)
    private Integer lesson_credit;

    public Lesson(String lesson_name, Integer lesson_credit) {
        this.lesson_name = lesson_name;
        this.lesson_credit = lesson_credit;
    }
}
