package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.ViewActivityController;
import com.fundraiser.backend.entity.FundRaisingActivity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fundraiser")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ActivityDashboard {

    private final ViewActivityController viewActivityController;

    // Step 1: Open activity dashboard
    @GetMapping("/activities/{fundRaiserId}")
    public ResponseEntity<?> loadActivities(@PathVariable String fundRaiserId) {

        // Step 2: loadActivities(fund_raiser_id)
        List<FundRaisingActivity> activities = viewActivityController.loadActivities(fundRaiserId);

        if (activities.isEmpty()) {
            // Step 6a: renderNoResult("No activities found")
            return ResponseEntity.ok(Map.of("message", "No activities found"));
        }

        // Step 6: renderActivityList(results)
        return ResponseEntity.ok(activities);
    }
}