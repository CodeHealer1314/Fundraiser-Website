package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.MonthlyReportController;
import com.fundraiser.backend.entity.PlatformReport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MonthlyReportPage {

    private final MonthlyReportController monthlyReportController;

    // GET /api/manager/reports/monthly
    // BCE boundary entry point — maps to sequence diagram step 2
    // "Click Generate Monthly Report"
    @GetMapping("/reports/monthly")
    public ResponseEntity<?> handleGenerate(
            @RequestParam String month) {

        // Step 3: generateMonthlyReport(month)
        PlatformReport report =
                monthlyReportController.generateMonthlyReport(month);

        if (report == null) {
            // Step 7a: renderNoResult("No report data found")
            return ResponseEntity.ok(
                    Map.of("message", "No report data found"));
        }

        // Step 7: renderReport(report)
        return ResponseEntity.ok(report);
    }
}