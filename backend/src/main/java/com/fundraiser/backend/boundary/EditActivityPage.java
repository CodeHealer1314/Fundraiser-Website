package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.UpdateActivityController;
import com.fundraiser.backend.entity.FundRaisingActivity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/fundraiser")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EditActivityPage {

    private final UpdateActivityController updateActivityController;

    // Step 2: loadActivity(activity_id)
    @GetMapping("/activities/{activityId}/edit")
    public ResponseEntity<?> loadActivity(@PathVariable String activityId) {

        // Step 2: loadActivity(activity_id)
        FundRaisingActivity activity = updateActivityController.loadActivity(activityId);

        if (activity == null) {
            // Step 5a: setErrorMessage("Activity not found")
            return ResponseEntity
                    .status(404)
                    .body(Map.of("message", "Activity not found"));
        }

        // Step 5: renderActivity(activity)
        return ResponseEntity.ok(activity);
    }

    // Step 8: updateActivity(activity_id, data)
    @PutMapping("/activities/{activityId}")
    public ResponseEntity<?> updateActivity(
            @PathVariable String activityId,
            @RequestBody ActivityUpdateRequest request) {

        // Build updated activity object
        FundRaisingActivity updated = new FundRaisingActivity();
        updated.setTitle(request.getTitle());
        updated.setDescription(request.getDescription());
        updated.setCategory(request.getCategory());
        updated.setGoalAmount(request.getGoalAmount());

        // Step 8: updateActivity(activity_id, data)
        boolean result = updateActivityController.updateActivity(activityId, updated);

        if (!result) {
            // Step 12a: setErrorMessage("Update failed")
            return ResponseEntity
                    .status(500)
                    .body(Map.of("message", "Update failed"));
        }

        // Step 12: setSuccessMessage("Activity updated successfully")
        return ResponseEntity.ok(Map.of("message", "Activity updated successfully"));
    }

    @Data
    public static class ActivityUpdateRequest {
        private String title;
        private String description;
        private String category;
        private double goalAmount;
    }
}