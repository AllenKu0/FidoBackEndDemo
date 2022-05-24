package com.example.springboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Belong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long belongId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pk_classromm", referencedColumnName = "classId")
    private ClassRoom classRoom;

    @OneToMany
    private Set<Teacher> teacher;

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

    public Set<Teacher> getTeacher() {
        return teacher;
    }

    public void setTeacher(Set<Teacher> teacher) {
        this.teacher = teacher;
    }

    public Belong(ClassRoom classRoom, Set<Teacher> teacher) {
        this.classRoom = classRoom;
        this.teacher = teacher;
    }
}
