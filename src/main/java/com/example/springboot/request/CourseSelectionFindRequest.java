package com.example.springboot.request;

public class CourseSelectionFindRequest {
    private String account;

    public CourseSelectionFindRequest() {
    }

    public CourseSelectionFindRequest(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
