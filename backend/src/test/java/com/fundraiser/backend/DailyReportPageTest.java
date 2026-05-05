package com.fundraiser.backend;

import com.fundraiser.backend.boundary.DailyReportPage;
import com.fundraiser.backend.controller.DailyReportController;
import com.fundraiser.backend.entity.PlatformReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DailyReportPageTest {

    @Mock
    private DailyReportController dailyReportController;

    @InjectMocks
    private DailyReportPage dailyReportPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleGenerate_reportFound_returns200() {
        // Arrange
        String selectedDate = "2024-01-01";

        PlatformReport report = new PlatformReport();
        report.setReportType("daily");
        report.setDateRange(selectedDate);
        report.setTotalActivities(10);
        report.setTotalDonations(5);
        report.setTotalAmount(1000.0);

        when(dailyReportController.generateDailyReport(selectedDate))
                .thenReturn(report);

        // Act
        ResponseEntity<?> response = dailyReportPage.handleGenerate(selectedDate);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(dailyReportController, times(1))
                .generateDailyReport(selectedDate);
    }

    @Test
    void handleGenerate_noReportData_returns200WithMessage() {
        // Arrange
        String selectedDate = "2024-01-01";

        when(dailyReportController.generateDailyReport(selectedDate))
                .thenReturn(null);

        // Act
        ResponseEntity<?> response = dailyReportPage.handleGenerate(selectedDate);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(dailyReportController, times(1))
                .generateDailyReport(selectedDate);
    }
}