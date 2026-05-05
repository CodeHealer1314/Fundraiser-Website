package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.PlatformReport;
import com.fundraiser.backend.repository.DonationRepository;
import com.fundraiser.backend.repository.FundRaisingActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonthlyReportController {

    private final DonationRepository donationRepository;
    private final FundRaisingActivityRepository fundRaisingActivityRepository;

    // Step 3: generateMonthlyReport(month)
    public PlatformReport generateMonthlyReport(String month) {

        // Step 4: getMonthlyReport(month)
        PlatformReport report = new PlatformReport();
        report.setReportType("monthly");
        report.setDateRange(month);

        // Get total activities for the month
        int totalActivities = fundRaisingActivityRepository.findAll().size();
        report.setTotalActivities(totalActivities);

        // Get total donations for the month
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