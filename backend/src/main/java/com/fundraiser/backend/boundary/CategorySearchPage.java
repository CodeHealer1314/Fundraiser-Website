package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.SearchCategoryController;
import com.fundraiser.backend.entity.ActivityCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CategorySearchPage {

    private final SearchCategoryController searchCategoryController;

    // GET /api/manager/categories/search
    // BCE boundary entry point — maps to sequence diagram step 2 "Click Search"
    @GetMapping("/categories/search")
    public ResponseEntity<?> handleSearch(
            @RequestParam(required = false) String keyword) {

        // Step 3: searchCategories(keyword)
        List<ActivityCategory> results =
                searchCategoryController.searchCategories(keyword);

        if (results == null || results.isEmpty()) {
            // Step 7a: renderNoResult("No categories found")
            return ResponseEntity.ok(
                    Map.of("message", "No categories found"));
        }

        // Step 7: renderCategoryList(results)
        return ResponseEntity.ok(results);
    }
}