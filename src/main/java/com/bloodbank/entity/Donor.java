package com.bloodbank.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "donors")
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "blood_group", nullable = false, length = 10)
    private String bloodGroup;

    @Column(name = "last_donation_date", nullable = false, length = 10)
    private String lastDonationDate;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    public Donor() {
    }

    public Donor(String name, String bloodGroup, String lastDonationDate, String phoneNumber) {
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.lastDonationDate = lastDonationDate;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getLastDonationDate() {
        return lastDonationDate;
    }

    public void setLastDonationDate(String lastDonationDate) {
        this.lastDonationDate = lastDonationDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEligibilityStatus() {
        if (lastDonationDate == null) {
            return "Eligible";
        }
        java.time.LocalDate lastDate = java.time.LocalDate.parse(lastDonationDate);
        java.time.LocalDate nextEligibleDate = lastDate.plusDays(90);
        if (nextEligibleDate.isAfter(java.time.LocalDate.now())) {
            return "Eligible on " + nextEligibleDate;
        } else {
            return "Eligible";
        }
    }
}
