package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long officeId;

    @Column(name = "officeName", columnDefinition = "varchar(68)", nullable = false)
    private String officeName;

    @Column(name = "officePhoneNumber", columnDefinition = "varchar(68)", nullable = false)
    private String officePhoneNumber;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Belong> belong;

    public Office(String officeName, String officePhoneNumber) {
        this.officeName = officeName;
        this.officePhoneNumber = officePhoneNumber;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getOfficePhoneNumber() {
        return officePhoneNumber;
    }

    public void setOfficePhoneNumber(String officePhoneNumber) {
        this.officePhoneNumber = officePhoneNumber;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public Set<Belong> getBelong() {
        return belong;
    }

    public void setBelong(Set<Belong> belong) {
        this.belong = belong;
    }
}
