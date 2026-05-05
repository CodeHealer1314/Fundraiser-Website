package com.fundraiser.backend;

import com.fundraiser.backend.controller.WeeklyReportController;
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

class WeeklyReportControllerTest {

    @Mock
    private DonationRepository donationRepository;

    @Mock
    private FundRaisingActivityRepository fundRaisingActivityRepository;

    @InjectMocks
    private WeeklyReportController weeklyReportController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateWeeklyReport_dataFound_returnsReport() {
        // Arrange
        String weekStart = "2024-01-01";

        when(donationRepository.findAll())
                .thenReturn(Collections.emptyList());
        when(fundRaisingActivityRepository.findAll())
                .thenReturn(Arrays.asList());

        // Act
        PlatformReport report =
                weeklyReportController.generateWeeklyReport(weekStart);

        // Assert
        assertNotNull(report);
        assertEquals("weekly", report.getReportType());
        assertEquals(weekStart, report.getDateRange());
    }

    @Test
    void generateWeeklyReport_noData_returnsEmptyReport() {
        // Arrange
        String weekStart = "2024-01-01";

        when(donationRepository.findAll())
                .thenReturn(Collections.emptyList());
        when(fundRaisingActivityRepository.findAll())
                .thenReturn(Collections.emptyList());

        // Act
        PlatformReport report =
                weeklyReportController.generateWeeklyReport(weekStart);

        // Assert
        assertNotNull(report);
        assertEquals(0, report.getTotalDonations());
        assertEquals(0, report.getTotalActivities());
    }
}