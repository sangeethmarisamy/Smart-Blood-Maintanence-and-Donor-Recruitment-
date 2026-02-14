package com.bloodbank.service;

import com.bloodbank.entity.StorageUnit;
import com.bloodbank.repository.StorageUnitRepository;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

    private final StorageUnitRepository storageUnitRepository;

    public StorageService(StorageUnitRepository storageUnitRepository) {
        this.storageUnitRepository = storageUnitRepository;
    }

    public StorageUnit getStorageUnit() {
        return storageUnitRepository.findById(1L).orElseGet(() -> {
            // Initialize default if not present
            StorageUnit defaultUnit = new StorageUnit(120.0, 500.0, "NORMAL");
            return storageUnitRepository.save(defaultUnit);
        });
    }

    public void updateWeight(Double newWeight) {
        StorageUnit unit = getStorageUnit();
        unit.setCurrentWeight(newWeight);
        // Simple logic to determine status based on weight
        // Assuming low weight is critical for refill
        if (newWeight <= 50.0) {
            unit.setStatus("CRITICAL");
        } else if (newWeight <= 100.0) {
            unit.setStatus("WARNING");
        } else {
            unit.setStatus("NORMAL");
        }
        storageUnitRepository.save(unit);
    }
}
