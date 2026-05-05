package com.fundraiser.backend;

import com.fundraiser.backend.boundary.WeeklyReportPage;
import com.fundraiser.backend.controller.WeeklyReportController;
import com.fundraiser.backend.entity.PlatformReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeeklyReportPageTest {

    @Mock
    private WeeklyReportController weeklyReportController;

    @InjectMocks
    private WeeklyReportPage weeklyReportPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleGenerate_reportFound_returns200() {
        // Arrange
        String weekStart = "2024-01-01";

        PlatformReport report = new PlatformReport();
        report.setReportType("weekly");
        report.setDateRange(weekStart);
        report.setTotalActivities(20);
        report.setTotalDonations(10);
        report.setTotalAmount(5000.0);

        when(weeklyReportController.generateWeeklyReport(weekStart))
                .thenReturn(report);

        // Act
        ResponseEntity<?> response = weeklyReportPage.handleGenerate(weekStart);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(weeklyReportController, times(1))
                .generateWeeklyReport(weekStart);
    }

    @Test
    void handleGenerate_noReportData_returns200WithMessage() {
        // Arrange
        String weekStart = "2024-01-01";

        when(weeklyReportController.generateWeeklyReport(weekStart))
                .thenReturn(null);

        // Act
        ResponseEntity<?> response = weeklyReportPage.handleGenerate(weekStart);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(weeklyReportController, times(1))
                .generateWeeklyReport(weekStart);
    }
}