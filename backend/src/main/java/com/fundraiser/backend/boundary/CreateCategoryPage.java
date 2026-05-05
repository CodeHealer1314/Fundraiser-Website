package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.CreateCategoryController;
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
public class CreateCategoryPage {

    private final CreateCategoryController createCategoryController;

    // POST /api/manager/categories
    // BCE boundary entry point — maps to sequence diagram step 2 "Click Create"
    @PostMapping("/categories")
    public ResponseEntity<?> handleSubmit(
            @RequestBody CategoryRequest request) {

        // Step 3: validateCategory(data)
        boolean isValid = createCategoryController.validateCategory(request);

        if (!isValid) {
            // Step 4a: showValidationErrors()
            return ResponseEntity
                    .status(400)
                    .body(Map.of("message", "Validation failed"));
        }

        // Step 5: createCategory(data)
        ActivityCategory category =
                createCategoryController.createCategory(request);

        // Step 9: setSuccessMessage("Category created successfully")
        return ResponseEntity.ok(Map.of(
                "message", "Category created successfully",
                "category", category));
    }

    // DTO - holds the incoming request data
    @Data
    public static class CategoryRequest {
        private String name;
        private String description;
    }
}