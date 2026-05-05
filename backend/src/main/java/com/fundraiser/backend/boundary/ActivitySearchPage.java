package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.SearchNewActivityController;
import com.fundraiser.backend.entity.FundRaisingActivity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/donee")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ActivitySearchPage {

    private final SearchNewActivityController searchNewActivityController;

    // GET /api/donee/activities/search
    // BCE boundary entry point — maps to sequence diagram step 2 "Click Search"
    @GetMapping("/activities/search")
    public ResponseEntity<?> clickSearch(
            @RequestParam(required = false) String keyword) {

        // Step 3: searchActivities(keyword)
        List<FundRaisingActivity> results =
                searchNewActivityController.searchActivities(keyword);

        if (results == null || results.isEmpty()) {
            // Step 7a: renderNoResult("No activities found")
            return ResponseEntity.ok(
                    Map.of("message", "No activities found"));
        }

        // Step 7: renderActivityList(results)
        return ResponseEntity.ok(results);
    }
}