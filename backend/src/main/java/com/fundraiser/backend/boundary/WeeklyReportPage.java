package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.WeeklyReportController;
import com.fundraiser.backend.entity.PlatformReport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WeeklyReportPage {

    private final WeeklyReportController weeklyReportController;

    // GET /api/manager/reports/weekly
    // BCE boundary entry point — maps to sequence diagram step 2
    // "Click Generate Weekly Report"
    @GetMapping("/reports/weekly")
    public ResponseEntity<?> handleGenerate(
            @RequestParam String weekStart) {

        // Step 3: generateWeeklyReport(week_start)
        PlatformReport report =
                weeklyReportController.generateWeeklyReport(weekStart);

        if (report == null) {
            // Step 7a: renderNoResult("No report data found")
            return ResponseEntity.ok(
                    Map.of("message", "No report data found"));
        }

        // Step 7: renderReport(report)
        return ResponseEntity.ok(report);
    }
}