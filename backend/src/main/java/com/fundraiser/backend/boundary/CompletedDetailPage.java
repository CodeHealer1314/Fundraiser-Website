package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.ViewCompletedController;
import com.fundraiser.backend.entity.FundRaisingActivity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/fundraiser")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CompletedDetailPage {

    private final ViewCompletedController viewCompletedController;

    // Step 2: loadCompletedActivity(activity_id)
    @GetMapping("/activities/{activityId}/completed")
    public ResponseEntity<?> loadCompletedActivity(@PathVariable String activityId) {

        // Step 2: loadCompletedActivity(activity_id)
        FundRaisingActivity activity = viewCompletedController
                .loadCompletedActivity(activityId);

        if (activity == null) {
            // Step 6a: setErrorMessage("Activity not found")
            return ResponseEntity
                    .status(404)
                    .body(Map.of("message", "Activity not found"));
        }

        // Step 6: renderCompletedActivityDetails(activity)
        return ResponseEntity.ok(activity);
    }
}
