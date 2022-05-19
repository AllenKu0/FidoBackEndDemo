package com.example.springboot.request;

import org.springframework.web.bind.annotation.RequestParam;

public class FinishAuthRequest {

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

    public String getCredname() {
        return credname;
    }

    public void setCredname(String credname) {
        this.credname = credname;
    }

    String credential;
     String username;
     String credname;
}
