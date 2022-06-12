package com.example.springboot.entity;

import com.example.springboot.request.UserRegisterRequest;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yubico.webauthn.data.ByteArray;
import com.yubico.webauthn.data.UserIdentity;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    public User(Long id) {
        this.id = id;
    }

    public User() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account", columnDefinition = "varchar(68)", nullable = false)
    private String account;
    @Column(name = "password", columnDefinition = "varchar(68)", nullable = false)
    private String password;


    @Column(name = "email", columnDefinition = "varchar(40)")
    @Nullable
    private String email;

    @JsonManagedReference
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private Set<CourseSelection> courseSelection;

    public Set<CourseSelection> getCourseSelection() {
        return courseSelection;
    }

    public void setCourseSelection(Set<CourseSelection> courseSelection) {
        this.courseSelection = courseSelection;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public User(UserRegisterRequest user) {
        this.account = user.getAccount();
        this.password = user.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
