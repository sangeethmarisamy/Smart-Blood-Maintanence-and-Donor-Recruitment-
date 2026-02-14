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
                    // Eligible Donors
                    new Donor("Amit Sharma", "O+", "2025-09-15", "+91 98765 43210"),
                    new Donor("Priya Patel", "A+", "2025-08-20", "+91 91234 56789"),
                    new Donor("Rahul Singh", "B+", "2025-07-01", "+91 99887 76655"),

                    // Ineligible Donors (Recent donations)
                    new Donor("Sneha Gupta", "AB+", "2026-01-25", "+91 98123 45678"),
                    new Donor("Vikram Malhotra", "O-", "2026-02-01", "+91 98456 12345"),
                    new Donor("Anjali Verma", "A-", "2026-01-10", "+91 87654 32109")));
        }
    }
}
