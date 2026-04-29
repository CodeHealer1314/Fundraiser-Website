package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.SearchActivityController;
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
    private final SearchActivityController searchActivityController;

    // UC#16: loadActivities
    @GetMapping("/activities/{fundRaiserId}")
    public ResponseEntity<?> loadActivities(@PathVariable String fundRaiserId) {

        List<FundRaisingActivity> activities = viewActivityController.loadActivities(fundRaiserId);

        if (activities.isEmpty()) {
            return ResponseEntity.ok(Map.of("message", "No activities found"));
        }

        return ResponseEntity.ok(activities);
    }

    // UC#19: searchActivities
    @GetMapping("/activities/{fundRaiserId}/search")
    public ResponseEntity<?> handleSearch(
            @PathVariable String fundRaiserId,
            @RequestParam String keyword) {

        // Step 3: searchActivities(keyword)
        List<FundRaisingActivity> results = searchActivityController
                .searchActivities(fundRaiserId, keyword);

        if (results.isEmpty()) {
            // Step 7a: renderNoResult("No activities found")
            return ResponseEntity.ok(Map.of("message", "No activities found"));
        }

        // Step 7: renderActivityList(results)
        return ResponseEntity.ok(results);
    }
}