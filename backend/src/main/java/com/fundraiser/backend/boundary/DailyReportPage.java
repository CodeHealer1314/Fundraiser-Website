package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.DailyReportController;
import com.fundraiser.backend.entity.PlatformReport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DailyReportPage {

    private final DailyReportController dailyReportController;

    // GET /api/manager/reports/daily
    // BCE boundary entry point — maps to sequence diagram step 2
    // "Click Generate Daily Report"
    @GetMapping("/reports/daily")
    public ResponseEntity<?> handleGenerate(
            @RequestParam String selectedDate) {

        // Step 3: generateDailyReport(selected_date)
        PlatformReport report =
                dailyReportController.generateDailyReport(selectedDate);

        if (report == null) {
            // Step 7a: renderNoResult("No report data found")
            return ResponseEntity.ok(
                    Map.of("message", "No report data found"));
        }

        // Step 7: renderReport(report)
        return ResponseEntity.ok(report);
    }
}