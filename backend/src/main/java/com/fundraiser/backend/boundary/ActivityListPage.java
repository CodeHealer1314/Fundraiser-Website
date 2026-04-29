package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.SuspendActivityController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/fundraiser")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ActivityListPage {

    private final SuspendActivityController suspendActivityController;

    // Step 4: handleSuspend(activity_id)
    @PatchMapping("/activities/{activityId}/suspend")
    public ResponseEntity<?> handleSuspend(@PathVariable String activityId) {

        // Step 4: handleSuspend(activity_id)
        boolean result = suspendActivityController.handleSuspend(activityId);

        if (!result) {
            // Activity not found
            return ResponseEntity
                    .status(404)
                    .body(Map.of("message", "Activity not found"));
        }

        // Step 8: setSuccessMessage("Activity suspended successfully")
        return ResponseEntity.ok(Map.of("message", "Activity suspended successfully"));
    }
}