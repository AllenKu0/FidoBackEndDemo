package com.example.springboot.entity;

import javax.persistence.*;

@Entity
public class User {
    public User(Long id)
    {
        this.id = id;
    }


    public User() {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "password", columnDefinition = "varchar(68)", nullable = false)
    private String password;

    @Column(name = "email", columnDefinition = "varchar(40)", nullable = false)
    private String email;

    @Column(name = "token" )
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
