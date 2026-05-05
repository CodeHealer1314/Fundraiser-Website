package com.fundraiser.backend;

import com.fundraiser.backend.boundary.MonthlyReportPage;
import com.fundraiser.backend.controller.MonthlyReportController;
import com.fundraiser.backend.entity.PlatformReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MonthlyReportPageTest {

    @Mock
    private MonthlyReportController monthlyReportController;

    @InjectMocks
    private MonthlyReportPage monthlyReportPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleGenerate_reportFound_returns200() {
        // Arrange
        String month = "2024-01";

        PlatformReport report = new PlatformReport();
        report.setReportType("monthly");
        report.setDateRange(month);
        report.setTotalActivities(50);
        report.setTotalDonations(30);
        report.setTotalAmount(15000.0);

        when(monthlyReportController.generateMonthlyReport(month))
                .thenReturn(report);

        // Act
        ResponseEntity<?> response = monthlyReportPage.handleGenerate(month);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(monthlyReportController, times(1))
                .generateMonthlyReport(month);
    }

    @Test
    void handleGenerate_noReportData_returns200WithMessage() {
        // Arrange
        String month = "2024-01";

        when(monthlyReportController.generateMonthlyReport(month))
                .thenReturn(null);

        // Act
        ResponseEntity<?> response = monthlyReportPage.handleGenerate(month);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(monthlyReportController, times(1))
                .generateMonthlyReport(month);
    }
}