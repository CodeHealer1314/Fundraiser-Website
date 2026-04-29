package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.SearchHistoryController;
import com.fundraiser.backend.entity.FundRaisingActivity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fundraiser")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CompletedHistoryPage {

    private final SearchHistoryController searchHistoryController;

    // Step 3: handleSearch()
    @GetMapping("/activities/{fundRaiserId}/history")
    public ResponseEntity<?> handleSearch(
            @PathVariable String fundRaiserId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to) {

        // Step 4: searchHistory(category, from, to)
        List<FundRaisingActivity> results = searchHistoryController
                .searchHistory(fundRaiserId, category, from, to);

        if (results.isEmpty()) {
            // Step 8a: renderNoResult("No completed activities found")
            return ResponseEntity.ok(
                    Map.of("message", "No completed activities found"));
        }

        // Step 8: renderHistoryList(results)
        return ResponseEntity.ok(results);
    }
}