package com.bloodbank.controller;

import com.bloodbank.service.DonorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final DonorService donorService;

    public DashboardController(DonorService donorService) {
        this.donorService = donorService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("eligibleDonors", donorService.getEligibleDonors());
        return "dashboard";
    }
}
