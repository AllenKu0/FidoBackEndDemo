package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Lesson {
    public Lesson(Long lessonId) {
        this.lessonId = lessonId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonId;

    @Column(name = "lessonName", columnDefinition = "varchar(68)", nullable = false)
    private String lessonName;

    @JsonManagedReference
    @OneToMany(mappedBy = "lesson",cascade = CascadeType.ALL)
    private Set<CourseSelection> courseSelection;

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Set<CourseSelection> getCourseSelection() {
        return courseSelection;
    }

    public void setCourseSelection(Set<CourseSelection> courseSelection) {
        this.courseSelection = courseSelection;
    }

    public Integer getLessonCredit() {
        return lessonCredit;
    }

    public void setLessonCredit(Integer lessonCredit) {
        this.lessonCredit = lessonCredit;
    }

    @Column(name = "lessonCredit",  nullable = false)
    private Integer lessonCredit;

    public Lesson(String lessonName, Integer lessonCredit) {
        this.lessonName = lessonName;
        this.lessonCredit = lessonCredit;
    }
}
