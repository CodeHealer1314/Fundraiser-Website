package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.UpdateCategoryController;
import com.fundraiser.backend.entity.ActivityCategory;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EditCategoryPage {

    private final UpdateCategoryController updateCategoryController;

    // GET /api/manager/categories/{categoryId}
    // BCE boundary entry point — maps to sequence diagram step 1 "Click category"
    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<?> loadCategory(@PathVariable String categoryId) {

        // Step 2: loadCategory(category_id)
        ActivityCategory category =
                updateCategoryController.loadCategory(categoryId);

        if (category == null) {
            return ResponseEntity
                    .status(404)
                    .body(Map.of("message", "Category not found"));
        }

        // Step 5: renderCategory(category)
        return ResponseEntity.ok(category);
    }

    // PUT /api/manager/categories/{categoryId}
    // BCE boundary entry point — maps to sequence diagram step 7 "Click Save Changes"
    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<?> updateCategory(
            @PathVariable String categoryId,
            @RequestBody CategoryRequest request) {

        // Step 8: updateCategory(category_id, data)
        boolean success = updateCategoryController
                .updateCategory(categoryId, request);

        if (!success) {
            // Step 12a: setErrorMessage("Update failed")
            return ResponseEntity
                    .status(400)
                    .body(Map.of("message", "Update failed"));
        }

        // Step 12: setSuccessMessage("Category updated successfully")
        return ResponseEntity.ok(
                Map.of("message", "Category updated successfully"));
    }

    // DTO - holds the incoming request data
    @Data
    public static class CategoryRequest {
        private String name;
        private String description;
    }
}