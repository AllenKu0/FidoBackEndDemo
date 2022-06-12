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
    @JoinColumn(name = "pk_office", referencedColumnName = "officeId")
    private Office office;

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


    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Belong(Office office, Teacher teacher) {
        this.office = office;
        this.teacher = teacher;
    }
}
