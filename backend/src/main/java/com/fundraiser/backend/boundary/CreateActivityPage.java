package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.CreateActivityController;
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
public class CreateActivityPage {

    private final CreateActivityController createActivityController;

    // Step 1: handleSubmit()
    @PostMapping("/activities")
    public ResponseEntity<?> handleSubmit(@RequestBody ActivityRequest request) {

        // Build activity object from request
        FundRaisingActivity activity = new FundRaisingActivity();
        activity.setTitle(request.getTitle());
        activity.setDescription(request.getDescription());
        activity.setCategory(request.getCategory());
        activity.setGoalAmount(request.getGoalAmount());
        activity.setFundRaiserId(request.getFundRaiserId());

        // Step 3: validateActivity(data)
        boolean isValid = createActivityController.validateActivity(activity);

        if (!isValid) {
            // Step 4a: showValidationErrors()
            return ResponseEntity
                    .status(400)
                    .body(Map.of("message", "Validation failed"));
        }

        // Step 5: createActivity(data)
        FundRaisingActivity created = createActivityController.createActivity(activity);

        // Step 9: setSuccessMessage("Activity created successfully")
        return ResponseEntity.ok(Map.of(
                "message", "Activity created successfully",
                "activity", created
        ));
    }

    @Data
    public static class ActivityRequest {
        private String title;
        private String description;
        private String category;
        private double goalAmount;
        private String fundRaiserId;
    }
}