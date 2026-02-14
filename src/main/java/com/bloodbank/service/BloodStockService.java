package com.bloodbank.service;

import com.bloodbank.entity.BloodStock;
import com.bloodbank.repository.BloodStockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BloodStockService {

    private final BloodStockRepository bloodStockRepository;
    private static final String[] BLOOD_TYPES = { "O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-" };

    public BloodStockService(BloodStockRepository bloodStockRepository) {
        this.bloodStockRepository = bloodStockRepository;
    }

    public List<BloodStock> getAllStock() {
        return bloodStockRepository.findAll();
    }

    public void seedStockIfEmpty() {
        if (bloodStockRepository.count() == 0) {
            Random random = new Random();
            for (String type : BLOOD_TYPES) {
                // Random available units between 0 and 50
                // Random required units between 0 and 50
                bloodStockRepository.save(new BloodStock(
                        type,
                        random.nextInt(51),
                        random.nextInt(51)));
            }
        }
    }
}
