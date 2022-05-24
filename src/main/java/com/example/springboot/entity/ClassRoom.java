package com.example.springboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long class_id;

    @Column(name = "className", columnDefinition = "varchar(68)", nullable = false)
    private String className;

    public Long getClassId() {
        return class_id;
    }

    public void setClassId(Long class_id) {
        this.class_id = class_id;
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
