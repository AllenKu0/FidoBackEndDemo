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

    @Column(name = "lessonCredit",  nullable = false)
    private Integer lessonCredit;

    @Column(name = "lessonStatus",  columnDefinition = "varchar(68)",nullable = false)
    private String lessonStatus;

    public String getLessonStatus() {
        return lessonStatus;
    }

    public void setLessonStatus(String lessonStatus) {
        this.lessonStatus = lessonStatus;
    }

    @OneToOne(mappedBy = "lesson",cascade = CascadeType.ALL)
    private Teach teach;

    @JsonManagedReference
    @OneToMany(mappedBy = "lesson",cascade = CascadeType.ALL)
    private Set<CourseSelection> courseSelection;

    @OneToOne(mappedBy = "lesson",cascade = CascadeType.ALL,orphanRemoval = true)
    private TeachOn teachOn;

    public TeachOn getTeachOn() {
        return teachOn;
    }

    public void setTeachOn(TeachOn teachOn) {
        this.teachOn = teachOn;
    }

    public Lesson(String lessonName, Integer lessonCredit,String lessonStatus) {
        this.lessonName = lessonName;
        this.lessonCredit = lessonCredit;
        this.lessonStatus = lessonStatus;
    }

    public Long getLessonId() {
        return lessonId;
    }
    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Teach getTeach() {
        return teach;
    }

    public void setTeach(Teach teach) {
        this.teach = teach;
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


}
