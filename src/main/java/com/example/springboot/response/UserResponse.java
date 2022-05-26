package com.example.springboot.response;

public class UserResponse {
    private Long id;
    private String userName;
    private String displayName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public UserResponse(Long id, String userName, String displayName) {
        this.id = id;
        this.userName = userName;
        this.displayName = displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
