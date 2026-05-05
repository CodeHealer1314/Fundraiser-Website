package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.ViewDonatedProgressController;
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
public class DonatedActivityProgressPage {

    private final ViewDonatedProgressController viewDonatedProgressController;

    // GET /api/donee/{doneeId}/donated/progress
    // BCE boundary entry point — maps to sequence diagram step 1 "openProgressPage"
    @GetMapping("/{doneeId}/donated/progress")
    public ResponseEntity<?> openProgressPage(@PathVariable String doneeId) {

        // Step 2: getDonatedActivityProgress(donee_id)
        List<FundRaisingActivity> results =
                viewDonatedProgressController.getDonatedActivityProgress(doneeId);

        if (results == null || results.isEmpty()) {
            // Step 6a: renderNoResult("No donated activities found")
            return ResponseEntity.ok(
                    Map.of("message", "No donated activities found"));
        }

        // Step 6: renderProgressList(results)
        return ResponseEntity.ok(results);
    }
}