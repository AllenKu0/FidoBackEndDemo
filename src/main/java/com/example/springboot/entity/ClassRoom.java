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

    @Column(name = "classPhoneNumber", columnDefinition = "varchar(68)", nullable = false)
    private String classPhoneNumber;

    @Column(name = "classLocation", columnDefinition = "varchar(68)", nullable = false)
    private String classLocation;

    @OneToMany(mappedBy = "classRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TeachOn> teachOns;

    public Set<TeachOn> getTeachOns() {
        return teachOns;
    }

    public void setTeachOns(Set<TeachOn> teachOns) {
        this.teachOns = teachOns;
    }

    public String getClassPhoneNumber() {
        return classPhoneNumber;
    }

    public void setClassPhoneNumber(String classPhoneNumber) {
        this.classPhoneNumber = classPhoneNumber;
    }

    public String getClassLocation() {
        return classLocation;
    }

    public void setClassLocation(String classLocation) {
        this.classLocation = classLocation;
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

    public void setClassName(String className) {
        this.className = className;
    }

    public ClassRoom(String className, String classPhoneNumber, String classLocation) {
        this.className = className;
        this.classPhoneNumber = classPhoneNumber;
        this.classLocation = classLocation;
    }
}
