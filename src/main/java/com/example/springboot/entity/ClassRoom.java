package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;

    @Column(name = "className", columnDefinition = "varchar(68)", nullable = false)
    private String className;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Belong> belong;

    public Set<Belong> getBelong() {
        return belong;
    }

    public void setBelong(Set<Belong> belong) {
        this.belong = belong;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public ClassRoom(String className) {
        this.className = className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
