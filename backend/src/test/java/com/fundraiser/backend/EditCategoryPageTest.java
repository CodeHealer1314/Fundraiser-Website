package com.fundraiser.backend;

import com.fundraiser.backend.boundary.EditCategoryPage;
import com.fundraiser.backend.controller.UpdateCategoryController;
import com.fundraiser.backend.entity.ActivityCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EditCategoryPageTest {

    @Mock
    private UpdateCategoryController updateCategoryController;

    @InjectMocks
    private EditCategoryPage editCategoryPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadCategory_categoryFound_returns200() {
        // Arrange
        String categoryId = "1";

        ActivityCategory category = new ActivityCategory();
        category.setName("Education");
        category.setDescription("Education related activities");

        when(updateCategoryController.loadCategory(categoryId))
                .thenReturn(category);

        // Act
        ResponseEntity<?> response = editCategoryPage.loadCategory(categoryId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(updateCategoryController, times(1)).loadCategory(categoryId);
    }

    @Test
    void loadCategory_categoryNotFound_returns404() {
        // Arrange
        String categoryId = "999";

        when(updateCategoryController.loadCategory(categoryId))
                .thenReturn(null);

        // Act
        ResponseEntity<?> response = editCategoryPage.loadCategory(categoryId);

        // Assert
        assertNotNull(response);
        assertEquals(404, response.getStatusCode().value());
        verify(updateCategoryController, times(1)).loadCategory(categoryId);
    }

    @Test
    void updateCategory_validData_returns200() {
        // Arrange
        String categoryId = "1";
        EditCategoryPage.CategoryRequest request =
                new EditCategoryPage.CategoryRequest();
        request.setName("Education Updated");
        request.setDescription("Updated description");

        when(updateCategoryController.updateCategory(categoryId, request))
                .thenReturn(true);

        // Act
        ResponseEntity<?> response =
                editCategoryPage.updateCategory(categoryId, request);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(updateCategoryController, times(1))
                .updateCategory(categoryId, request);
    }

    @Test
    void updateCategory_invalidData_returns400() {
        // Arrange
        String categoryId = "1";
        EditCategoryPage.CategoryRequest request =
                new EditCategoryPage.CategoryRequest();
        request.setName("");
        request.setDescription("");

        when(updateCategoryController.updateCategory(categoryId, request))
                .thenReturn(false);

        // Act
        ResponseEntity<?> response =
                editCategoryPage.updateCategory(categoryId, request);

        // Assert
        assertNotNull(response);
        assertEquals(400, response.getStatusCode().value());
        verify(updateCategoryController, times(1))
                .updateCategory(categoryId, request);
    }
}