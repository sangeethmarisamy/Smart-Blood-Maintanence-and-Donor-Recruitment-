package com.bloodbank.controller;

import com.bloodbank.service.DonorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final DonorService donorService;
    private final com.bloodbank.service.BloodStockService bloodStockService;

    public DashboardController(DonorService donorService, com.bloodbank.service.BloodStockService bloodStockService) {
        this.donorService = donorService;
        this.bloodStockService = bloodStockService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("eligibleDonors", donorService.getEligibleDonors());
        model.addAttribute("bloodStocks", bloodStockService.getAllStock());
        return "dashboard";
    }
}
