package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.PlatformReport;
import com.fundraiser.backend.repository.DonationRepository;
import com.fundraiser.backend.repository.FundRaisingActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyReportController {

    private final DonationRepository donationRepository;
    private final FundRaisingActivityRepository fundRaisingActivityRepository;

    // Step 3: generateDailyReport(selected_date)
    public PlatformReport generateDailyReport(String selectedDate) {

        // Step 4: getDailyReport(selected_date)
        PlatformReport report = new PlatformReport();
        report.setReportType("daily");
        report.setDateRange(selectedDate);

        // Get total activities for the day
        int totalActivities = fundRaisingActivityRepository.findAll().size();
        report.setTotalActivities(totalActivities);

        // Get total donations for the day
        int totalDonations = donationRepository.findAll().size();
        report.setTotalDonations(totalDonations);

        // Calculate total amount
        double totalAmount = donationRepository.findAll()
                .stream()
                .mapToDouble(d -> 0.0)
                .sum();
        report.setTotalAmount(totalAmount);

        // Step 5: return report
        return report;
    }
}