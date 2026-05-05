package com.fundraiser.backend;

import com.fundraiser.backend.boundary.CreateCategoryPage;
import com.fundraiser.backend.controller.CreateCategoryController;
import com.fundraiser.backend.entity.ActivityCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCategoryPageTest {

    @Mock
    private CreateCategoryController createCategoryController;

    @InjectMocks
    private CreateCategoryPage createCategoryPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleSubmit_validData_returns200() {
        // Arrange
        CreateCategoryPage.CategoryRequest request =
                new CreateCategoryPage.CategoryRequest();
        request.setName("Education");
        request.setDescription("Education related fund raising activities");

        ActivityCategory category = new ActivityCategory();
        category.setName("Education");
        category.setDescription("Education related fund raising activities");

        when(createCategoryController.validateCategory(request))
                .thenReturn(true);
        when(createCategoryController.createCategory(request))
                .thenReturn(category);

        // Act
        ResponseEntity<?> response = createCategoryPage.handleSubmit(request);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(createCategoryController, times(1)).validateCategory(request);
        verify(createCategoryController, times(1)).createCategory(request);
    }

    @Test
    void handleSubmit_invalidData_returns400() {
        // Arrange
        CreateCategoryPage.CategoryRequest request =
                new CreateCategoryPage.CategoryRequest();
        request.setName("");
        request.setDescription("");

        when(createCategoryController.validateCategory(request))
                .thenReturn(false);

        // Act
        ResponseEntity<?> response = createCategoryPage.handleSubmit(request);

        // Assert
        assertNotNull(response);
        assertEquals(400, response.getStatusCode().value());
        verify(createCategoryController, times(1)).validateCategory(request);
        verify(createCategoryController, never()).createCategory(request);
    }
}