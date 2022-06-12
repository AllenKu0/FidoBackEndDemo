package com.example.springboot.response;

public class OfficeResponse {
    private Long officeId;
    private String officeName;
    private String officePhoneNumber;

    public OfficeResponse(Long officeId, String officeName, String officePhoneNumber) {
        this.officeId = officeId;
        this.officeName = officeName;
        this.officePhoneNumber = officePhoneNumber;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public String getOfficePhoneNumber() {
        return officePhoneNumber;
    }

    public void setOfficePhoneNumber(String officePhoneNumber) {
        this.officePhoneNumber = officePhoneNumber;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }


}
