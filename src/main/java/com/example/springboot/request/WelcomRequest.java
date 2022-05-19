package com.example.springboot.request;

import org.springframework.web.bind.annotation.RequestParam;

public class WelcomRequest {
    String credential;
    String username;

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
