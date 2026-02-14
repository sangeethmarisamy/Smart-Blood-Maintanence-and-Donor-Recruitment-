package com.bloodbank.controller;

import com.bloodbank.service.DonorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final DonorService donorService;
    private final com.bloodbank.service.BloodStockService bloodStockService;
    private final com.bloodbank.service.StorageService storageService;

    public DashboardController(DonorService donorService,
                               com.bloodbank.service.BloodStockService bloodStockService,
                               com.bloodbank.service.StorageService storageService) {
        this.donorService = donorService;
        this.bloodStockService = bloodStockService;
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        java.util.List<com.bloodbank.entity.BloodStock> allStock = bloodStockService.getAllStock();
        java.util.List<String> lowStockGroups = allStock.stream()
                .filter(s -> s.getAvailableUnits() < s.getRequiredUnits())
                .map(com.bloodbank.entity.BloodStock::getBloodGroup)
                .collect(java.util.stream.Collectors.toList());

        java.util.List<com.bloodbank.entity.Donor> allEligible = donorService.getEligibleDonors();
        java.util.List<com.bloodbank.entity.Donor> filteredDonors = allEligible.stream()
                .filter(d -> lowStockGroups.contains(d.getBloodGroup()))
                .collect(java.util.stream.Collectors.toList());

        model.addAttribute("eligibleDonors", filteredDonors);
        model.addAttribute("bloodStocks", allStock);
        model.addAttribute("storageUnit", storageService.getStorageUnit());
        return "dashboard";
    }

    @org.springframework.web.bind.annotation.PostMapping("/api/update-weight")
    @org.springframework.web.bind.annotation.ResponseBody
    public org.springframework.http.ResponseEntity<Void> updateWeight(@org.springframework.web.bind.annotation.RequestBody java.util.Map<String, Double> payload) {
        if (payload.containsKey("weight")) {
            storageService.updateWeight(payload.get("weight"));
            return org.springframework.http.ResponseEntity.ok().build();
        }
        return org.springframework.http.ResponseEntity.badRequest().build();
    }
}
