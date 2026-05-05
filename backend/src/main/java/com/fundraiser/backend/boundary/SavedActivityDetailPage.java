package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.ViewSavedActivityController;
import com.fundraiser.backend.entity.FundRaisingActivity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/donee")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SavedActivityDetailPage {

    private final ViewSavedActivityController viewSavedActivityController;

    // GET /api/donee/{doneeId}/saved/{activityId}
    // BCE boundary entry point — maps to sequence diagram step 1 "clickSavedActivity"
    @GetMapping("/{doneeId}/saved/{activityId}")
    public ResponseEntity<?> clickSavedActivity(
            @PathVariable String doneeId,
            @PathVariable String activityId) {

        // Step 2: loadSavedActivityDetails(donee_id, activity_id)
        FundRaisingActivity activity =
                viewSavedActivityController.loadSavedActivityDetails(
                        doneeId, activityId);

        if (activity == null) {
            // Step 6a: setErrorMessage("Activity not found")
            return ResponseEntity
                    .status(404)
                    .body(Map.of("message", "Activity not found"));
        }

        // Step 6: renderSavedActivityDetails(details)
        return ResponseEntity.ok(activity);
    }
}