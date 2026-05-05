package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.ViewCategoryController;
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
public class CategoryListPage {

    private final ViewCategoryController viewCategoryController;

    // GET /api/manager/categories
    // BCE boundary entry point — maps to sequence diagram step 1 "Open category list"
    @GetMapping("/categories")
    public ResponseEntity<?> loadCategories() {

        // Step 2: loadCategories()
        List<ActivityCategory> results =
                viewCategoryController.loadCategories();

        if (results == null || results.isEmpty()) {
            // Step 6a: renderNoResult("No categories found")
            return ResponseEntity.ok(
                    Map.of("message", "No categories found"));
        }

        // Step 6: renderCategoryList(results)
        return ResponseEntity.ok(results);
    }
}