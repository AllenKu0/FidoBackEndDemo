package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "TrelationC")
@Getter
@NoArgsConstructor
public class Belong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long belongId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "pk_classroom", referencedColumnName = "classId")
    private ClassRoom classRoom;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "pk_teacher", referencedColumnName = "teacherId")
    private Teacher teacher;

    public Long getBelongId() {
        return belongId;
    }

    public void setBelongId(Long belongId) {
        this.belongId = belongId;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Belong(ClassRoom classRoom, Teacher teacher) {
        this.classRoom = classRoom;
        this.teacher = teacher;
    }
}
