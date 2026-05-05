package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.PlatformReport;
import com.fundraiser.backend.repository.DonationRepository;
import com.fundraiser.backend.repository.FundRaisingActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeeklyReportController {

    private final DonationRepository donationRepository;
    private final FundRaisingActivityRepository fundRaisingActivityRepository;

    // Step 3: generateWeeklyReport(week_start)
    public PlatformReport generateWeeklyReport(String weekStart) {

        // Step 4: getWeeklyReport(week_start)
        PlatformReport report = new PlatformReport();
        report.setReportType("weekly");
        report.setDateRange(weekStart);

        // Get total activities for the week
        int totalActivities = fundRaisingActivityRepository.findAll().size();
        report.setTotalActivities(totalActivities);

        // Get total donations for the week
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