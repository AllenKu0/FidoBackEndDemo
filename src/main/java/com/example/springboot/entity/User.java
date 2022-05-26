package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yubico.webauthn.data.ByteArray;
import com.yubico.webauthn.data.UserIdentity;

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

    @Column(name = "password", columnDefinition = "varchar(68)", nullable = false)
    private String password;

    @Column(name = "userName", columnDefinition = "varchar(40)", nullable = false)
    private String userName;

    @Column(name = "token")
    private String token;

    @Column(nullable = false)
    private String displayName;

    @Lob
    @Column(nullable = false, length = 64)
    private ByteArray handle;

    @JsonManagedReference
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private Set<CourseSelection> courseSelection;

    public Set<CourseSelection> getCourseSelection() {
        return courseSelection;
    }

    public void setCourseSelection(Set<CourseSelection> courseSelection) {
        this.courseSelection = courseSelection;
    }

    public User(UserIdentity user) {
        this.handle = user.getId();
        this.userName = user.getName();
        this.displayName = user.getDisplayName();
    }

    public UserIdentity toUserIdentity() {
        return UserIdentity.builder()
                .name(getUserName())
                .displayName(getDisplayName())
                .id(getHandle())
                .build();
    }


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public ByteArray getHandle() {
        return handle;
    }

    public void setHandle(ByteArray handle) {
        this.handle = handle;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
