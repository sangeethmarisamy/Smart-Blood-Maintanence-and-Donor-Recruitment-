package com.bloodbank.config;

import com.bloodbank.service.DonorService;
import com.bloodbank.service.UserService;
import com.bloodbank.service.BloodStockService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DonorService donorService;
    private final UserService userService;
    private final BloodStockService bloodStockService;

    public DataInitializer(DonorService donorService, UserService userService, BloodStockService bloodStockService) {
        this.donorService = donorService;
        this.userService = userService;
        this.bloodStockService = bloodStockService;
    }

    @Override
    public void run(String... args) {
        donorService.seedSampleDonorsIfEmpty();
        userService.seedAdminIfEmpty();
        bloodStockService.seedStockIfEmpty();
    }
}
