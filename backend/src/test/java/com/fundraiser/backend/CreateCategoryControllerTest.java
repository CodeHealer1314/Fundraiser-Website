package com.fundraiser.backend;

import com.fundraiser.backend.boundary.CreateCategoryPage;
import com.fundraiser.backend.controller.CreateCategoryController;
import com.fundraiser.backend.entity.ActivityCategory;
import com.fundraiser.backend.repository.ActivityCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCategoryControllerTest {

    @Mock
    private ActivityCategoryRepository activityCategoryRepository;

    @InjectMocks
    private CreateCategoryController createCategoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validateCategory_validData_returnsTrue() {
        // Arrange
        CreateCategoryPage.CategoryRequest request =
                new CreateCategoryPage.CategoryRequest();
        request.setName("Education");
        request.setDescription("Education related fund raising activities");

        // Act
        boolean result = createCategoryController.validateCategory(request);

        // Assert
        assertTrue(result);
    }

    @Test
    void createCategory_validData_returnsCategory() {
        // Arrange
        CreateCategoryPage.CategoryRequest request =
                new CreateCategoryPage.CategoryRequest();
        request.setName("Education");
        request.setDescription("Education related fund raising activities");

        ActivityCategory category = new ActivityCategory();
        category.setName("Education");
        category.setDescription("Education related fund raising activities");

        when(activityCategoryRepository.save(any(ActivityCategory.class)))
                .thenReturn(category);

        // Act
        ActivityCategory result = createCategoryController.createCategory(request);

        // Assert
        assertNotNull(result);
        assertEquals("Education", result.getName());
        verify(activityCategoryRepository, times(1))
                .save(any(ActivityCategory.class));
    }
}