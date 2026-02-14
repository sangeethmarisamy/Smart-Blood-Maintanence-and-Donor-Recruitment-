package com.bloodbank.service;

import com.bloodbank.entity.Donor;
import com.bloodbank.repository.DonorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DonorService {

    private static final int DAYS_ELIGIBLE = 90;

    private final DonorRepository donorRepository;

    public DonorService(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    public List<Donor> getEligibleDonors() {
        return getEligibleDonors(null);
    }

    public List<Donor> getEligibleDonors(String bloodType) {
        LocalDate today = LocalDate.now();
        LocalDate ninetyDaysAgo = today.minusDays(DAYS_ELIGIBLE);
        String cutoffDate = ninetyDaysAgo.format(DateTimeFormatter.ISO_LOCAL_DATE);

        return bloodType == null || bloodType.isBlank()
                ? donorRepository.findByLastDonationDateLessThanEqualOrderByNameAsc(cutoffDate)
                : donorRepository.findByBloodGroupAndLastDonationDateLessThanEqualOrderByNameAsc(bloodType, cutoffDate);
    }

    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    public List<Donor> getDonorsByBloodType(String bloodType) {
        return bloodType == null || bloodType.isBlank()
                ? donorRepository.findAll()
                : donorRepository.findByBloodGroupOrderByNameAsc(bloodType);
    }

    public Donor saveDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    public void seedSampleDonorsIfEmpty() {
        if (donorRepository.count() == 0) {
            donorRepository.saveAll(List.of(
                    new Donor("John Smith", "O+", "2024-09-15"),
                    new Donor("Jane Doe", "O+", "2024-08-20"),
                    new Donor("Mike Ross", "O+", "2024-07-01"),
                    new Donor("Sarah Wilson", "A+", "2025-01-25"),
                    new Donor("Tom Brown", "B-", "2025-02-01")
            ));
        }
    }
}
