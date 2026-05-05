package com.fundraiser.backend;

import com.fundraiser.backend.boundary.CategoryListPage;
import com.fundraiser.backend.controller.ViewCategoryController;
import com.fundraiser.backend.entity.ActivityCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryListPageTest {

    @Mock
    private ViewCategoryController viewCategoryController;

    @InjectMocks
    private CategoryListPage categoryListPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadCategories_categoriesFound_returns200() {
        // Arrange
        ActivityCategory category = new ActivityCategory();
        category.setName("Education");
        category.setDescription("Education related activities");

        when(viewCategoryController.loadCategories())
                .thenReturn(Arrays.asList(category));

        // Act
        ResponseEntity<?> response = categoryListPage.loadCategories();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(viewCategoryController, times(1)).loadCategories();
    }

    @Test
    void loadCategories_noCategories_returns200WithMessage() {
        // Arrange
        when(viewCategoryController.loadCategories())
                .thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<?> response = categoryListPage.loadCategories();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(viewCategoryController, times(1)).loadCategories();
    }
}