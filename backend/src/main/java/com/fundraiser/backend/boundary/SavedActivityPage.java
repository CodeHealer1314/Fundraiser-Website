package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.SearchSavedActivityController;
import com.fundraiser.backend.entity.FavouriteActivity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/donee")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SavedActivityPage {

    private final SearchSavedActivityController searchSavedActivityController;

    // GET /api/donee/{doneeId}/saved/search
    // BCE boundary entry point — maps to sequence diagram step 2 "Click Search"
    @GetMapping("/{doneeId}/saved/search")
    public ResponseEntity<?> clickSearch(
            @PathVariable String doneeId,
            @RequestParam(required = false) String keyword) {

        // Step 3: searchSavedActivities(donee_id, keyword)
        List<FavouriteActivity> results =
                searchSavedActivityController.searchSavedActivities(doneeId, keyword);

        if (results == null || results.isEmpty()) {
            // Step 7a: renderNoResult("No saved activities found")
            return ResponseEntity.ok(
                    Map.of("message", "No saved activities found"));
        }

        // Step 7: renderSavedActivities(results)
        return ResponseEntity.ok(results);
    }
}