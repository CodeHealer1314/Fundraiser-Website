package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.SearchDonationHistoryController;
import com.fundraiser.backend.entity.Donation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/donee")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DonationHistoryPage {

    private final SearchDonationHistoryController searchDonationHistoryController;

    // GET /api/donee/{doneeId}/donations/history
    // BCE boundary entry point — maps to sequence diagram step 3 "Click Search"
    @GetMapping("/{doneeId}/donations/history")
    public ResponseEntity<?> clickSearch(
            @PathVariable String doneeId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String dateFrom,
            @RequestParam(required = false) String dateTo) {

        // Step 4: searchDonationHistory(donee_id, category, date_from, date_to)
        List<Donation> results = searchDonationHistoryController
                .searchDonationHistory(doneeId, category, dateFrom, dateTo);

        if (results == null || results.isEmpty()) {
            // Step 8a: renderNoResult("No donation history found")
            return ResponseEntity.ok(
                    Map.of("message", "No donation history found"));
        }

        // Step 8: renderHistoryList(results)
        return ResponseEntity.ok(results);
    }
}