package com.fundraiser.backend;

import com.fundraiser.backend.boundary.CategoryListPage;
import com.fundraiser.backend.controller.DeleteCategoryController;
import com.fundraiser.backend.controller.ViewCategoryController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryListPageDeleteTest {

    @Mock
    private ViewCategoryController viewCategoryController;

    @Mock
    private DeleteCategoryController deleteCategoryController;

    @InjectMocks
    private CategoryListPage categoryListPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void openDeleteModal_validId_returns200() {
        // Arrange
        String categoryId = "1";

        when(deleteCategoryController.handleDelete(categoryId))
                .thenReturn(true);

        // Act
        ResponseEntity<?> response =
                categoryListPage.openDeleteModal(categoryId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(deleteCategoryController, times(1)).handleDelete(categoryId);
    }

    @Test
    void openDeleteModal_invalidId_returns400() {
        // Arrange
        String categoryId = "999";

        when(deleteCategoryController.handleDelete(categoryId))
                .thenReturn(false);

        // Act
        ResponseEntity<?> response =
                categoryListPage.openDeleteModal(categoryId);

        // Assert
        assertNotNull(response);
        assertEquals(400, response.getStatusCode().value());
        verify(deleteCategoryController, times(1)).handleDelete(categoryId);
    }
}