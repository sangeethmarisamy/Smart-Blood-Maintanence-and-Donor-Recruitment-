package com.bloodbank.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "storage_unit")
public class StorageUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "current_weight", nullable = false)
    private Double currentWeight; // In Grams

    @Column(name = "max_weight", nullable = false)
    private Double maxWeight; // In Grams

    @Column(name = "status", nullable = false)
    private String status; // "NORMAL", "WARNING", "CRITICAL"

    public StorageUnit() {
    }

    public StorageUnit(Double currentWeight, Double maxWeight, String status) {
        this.currentWeight = currentWeight;
        this.maxWeight = maxWeight;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
