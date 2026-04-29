package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.ShortlistCountController;
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
    private final ShortlistCountController shortlistCountController;

    // UC#20: renderViewCount
    @GetMapping("/activities/{activityId}/viewcount")
    public ResponseEntity<?> renderViewCount(@PathVariable String activityId) {

        int count = viewCountController.getViewCount(activityId);
        return ResponseEntity.ok(Map.of("viewCount", count));
    }

    // UC#21: renderShortlistCount
    @GetMapping("/activities/{activityId}/shortlistcount")
    public ResponseEntity<?> renderShortlistCount(@PathVariable String activityId) {

        // Step 2: getShortlistCount(activity_id)
        int count = shortlistCountController.getShortlistCount(activityId);

        // Step 6: renderShortlistCount(count)
        return ResponseEntity.ok(Map.of("shortlistCount", count));
    }
}