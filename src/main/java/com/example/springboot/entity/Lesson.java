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

}
