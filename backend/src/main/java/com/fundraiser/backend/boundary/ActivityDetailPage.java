package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.ViewCountController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/fundraiser")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ActivityDetailPage {

    private final ViewCountController viewCountController;

    // Step 2: getViewCount(activity_id)
    @GetMapping("/activities/{activityId}/viewcount")
    public ResponseEntity<?> renderViewCount(@PathVariable String activityId) {

        // Step 2: getViewCount(activity_id)
        int count = viewCountController.getViewCount(activityId);

        // Step 6: renderViewCount(count)
        return ResponseEntity.ok(Map.of("viewCount", count));
    }
}