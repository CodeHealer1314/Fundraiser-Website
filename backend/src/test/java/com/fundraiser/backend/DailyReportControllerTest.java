package com.fundraiser.backend;

import com.fundraiser.backend.controller.DailyReportController;
import com.fundraiser.backend.entity.PlatformReport;
import com.fundraiser.backend.repository.DonationRepository;
import com.fundraiser.backend.repository.FundRaisingActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DailyReportControllerTest {

    @Mock
    private DonationRepository donationRepository;

    @Mock
    private FundRaisingActivityRepository fundRaisingActivityRepository;

    @InjectMocks
    private DailyReportController dailyReportController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateDailyReport_dataFound_returnsReport() {
        // Arrange
        String selectedDate = "2024-01-01";

        when(donationRepository.findByDoneeIdAndCategoryAndDonationDateBetween(
                any(), any(), any(), any()))
                .thenReturn(Collections.emptyList());

        when(fundRaisingActivityRepository.findAll())
                .thenReturn(Arrays.asList());

        // Act
        PlatformReport report =
                dailyReportController.generateDailyReport(selectedDate);

        // Assert
        assertNotNull(report);
        assertEquals("daily", report.getReportType());
        assertEquals(selectedDate, report.getDateRange());
    }

    @Test
    void generateDailyReport_noData_returnsEmptyReport() {
        // Arrange
        String selectedDate = "2024-01-01";

        when(donationRepository.findByDoneeIdAndCategoryAndDonationDateBetween(
                any(), any(), any(), any()))
                .thenReturn(Collections.emptyList());

        when(fundRaisingActivityRepository.findAll())
                .thenReturn(Collections.emptyList());

        // Act
        PlatformReport report =
                dailyReportController.generateDailyReport(selectedDate);

        // Assert
        assertNotNull(report);
        assertEquals(0, report.getTotalDonations());
        assertEquals(0, report.getTotalActivities());
    }
}