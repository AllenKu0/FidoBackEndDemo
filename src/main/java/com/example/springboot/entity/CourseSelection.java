package com.example.springboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CourseSelection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long choose_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pk_lesson", referencedColumnName = "lesson_id")
    private Lesson lesson;

    @ManyToOne
    @JoinColumn(name = "pk_user", referencedColumnName = "id")
    private User user;
}
