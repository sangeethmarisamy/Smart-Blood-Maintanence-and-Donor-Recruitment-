package com.bloodbank.controller;

import com.bloodbank.entity.Donor;
import com.bloodbank.service.DonorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/donors")
public class DonorController {

    private static final String[] BLOOD_TYPES = {"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};

    private final DonorService donorService;

    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @GetMapping
    public String donorList(@RequestParam(required = false) String bloodType, Model model) {
        List<Donor> donors = donorService.getDonorsByBloodType(bloodType);
        model.addAttribute("donors", donors);
        model.addAttribute("eligibleDonors", donorService.getEligibleDonors());
        model.addAttribute("bloodTypes", BLOOD_TYPES);
        model.addAttribute("selectedBloodType", bloodType != null ? bloodType : "");
        return "donors";
    }
}
