package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    @Column(name = "teacherName", columnDefinition = "varchar(68)", nullable = false)
    private String teacherName;

    @JsonManagedReference
    @OneToOne(mappedBy = "teacher",cascade = CascadeType.ALL)
    private Belong belong;


    @OneToOne(mappedBy = "teacher",cascade = CascadeType.ALL)
    private Teach teach;

    public Teach getTeach() {
        return teach;
    }

    public void setTeach(Teach teach) {
        this.teach = teach;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Belong getBelong() {
        return belong;
    }

    public void setBelong(Belong belong) {
        this.belong = belong;
    }

    public Teacher(String teacher_name) {
        this.teacherName = teacher_name;
    }
}
