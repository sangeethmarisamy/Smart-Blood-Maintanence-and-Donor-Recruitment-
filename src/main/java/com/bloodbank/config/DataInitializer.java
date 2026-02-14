package com.bloodbank.config;

import com.bloodbank.service.DonorService;
import com.bloodbank.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DonorService donorService;
    private final UserService userService;

    public DataInitializer(DonorService donorService, UserService userService) {
        this.donorService = donorService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        donorService.seedSampleDonorsIfEmpty();
        userService.seedAdminIfEmpty();
    }
}
