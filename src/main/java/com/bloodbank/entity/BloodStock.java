package com.bloodbank.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "blood_stock")
public class BloodStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "blood_group", nullable = false, unique = true, length = 5)
    private String bloodGroup;

    @Column(name = "available_units", nullable = false)
    private int availableUnits;

    @Column(name = "required_units", nullable = false)
    private int requiredUnits;

    public BloodStock() {
    }

    public BloodStock(String bloodGroup, int availableUnits, int requiredUnits) {
        this.bloodGroup = bloodGroup;
        this.availableUnits = availableUnits;
        this.requiredUnits = requiredUnits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getAvailableUnits() {
        return availableUnits;
    }

    public void setAvailableUnits(int availableUnits) {
        this.availableUnits = availableUnits;
    }

    public int getRequiredUnits() {
        return requiredUnits;
    }

    public void setRequiredUnits(int requiredUnits) {
        this.requiredUnits = requiredUnits;
    }
}
