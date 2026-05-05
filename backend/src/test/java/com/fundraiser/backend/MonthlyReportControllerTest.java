package com.fundraiser.backend;

import com.fundraiser.backend.controller.MonthlyReportController;
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

class MonthlyReportControllerTest {

    @Mock
    private DonationRepository donationRepository;

    @Mock
    private FundRaisingActivityRepository fundRaisingActivityRepository;

    @InjectMocks
    private MonthlyReportController monthlyReportController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateMonthlyReport_dataFound_returnsReport() {
        // Arrange
        String month = "2024-01";

        when(donationRepository.findAll())
                .thenReturn(Collections.emptyList());
        when(fundRaisingActivityRepository.findAll())
                .thenReturn(Arrays.asList());

        // Act
        PlatformReport report =
                monthlyReportController.generateMonthlyReport(month);

        // Assert
        assertNotNull(report);
        assertEquals("monthly", report.getReportType());
        assertEquals(month, report.getDateRange());
    }

    @Test
    void generateMonthlyReport_noData_returnsEmptyReport() {
        // Arrange
        String month = "2024-01";

        when(donationRepository.findAll())
                .thenReturn(Collections.emptyList());
        when(fundRaisingActivityRepository.findAll())
                .thenReturn(Collections.emptyList());

        // Act
        PlatformReport report =
                monthlyReportController.generateMonthlyReport(month);

        // Assert
        assertNotNull(report);
        assertEquals(0, report.getTotalDonations());
        assertEquals(0, report.getTotalActivities());
    }
}