package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.DoneeViewActivityController;
import com.fundraiser.backend.controller.SaveFavouriteController;
import com.fundraiser.backend.entity.FundRaisingActivity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/donee")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DoneeActivityDetailPage {

    private final DoneeViewActivityController doneeViewActivityController;
    private final SaveFavouriteController saveFavouriteController;

    // GET /api/donee/activities/{activityId}
    // BCE boundary entry point — maps to sequence diagram step 1 "clickActivity"
    @GetMapping("/activities/{activityId}")
    public ResponseEntity<?> clickActivity(@PathVariable String activityId) {

        // Step 2: loadActivityDetails(activity_id)
        FundRaisingActivity activity =
                doneeViewActivityController.loadActivityDetails(activityId);

        if (activity == null) {
            // Step 6a: setErrorMessage("Activity not found")
            return ResponseEntity
                    .status(404)
                    .body(Map.of("message", "Activity not found"));
        }

        // Step 6: renderActivityDetails(details)
        return ResponseEntity.ok(activity);
    }

    // POST /api/donee/{doneeId}/activities/{activityId}/save
    // BCE boundary entry point — maps to sequence diagram step 1 "Click Save"
    @PostMapping("/{doneeId}/activities/{activityId}/save")
    public ResponseEntity<?> clickSave(
            @PathVariable String doneeId,
            @PathVariable String activityId) {

        // Step 2: handleSaveFavourite(donee_id, activity_id)
        boolean success = saveFavouriteController
                .handleSaveFavourite(doneeId, activityId);

        if (!success) {
            // Step 6a: showErrorMessage("Unable to save activity")
            return ResponseEntity
                    .status(400)
                    .body(Map.of("message", "Unable to save activity"));
        }

        // Step 6: setSuccessMessage("Activity saved successfully")
        return ResponseEntity.ok(Map.of("message", "Activity saved successfully"));
    }
}
