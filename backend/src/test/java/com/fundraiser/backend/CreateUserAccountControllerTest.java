package com.fundraiser.backend;

import com.fundraiser.backend.controller.CreateUserAccountController;
import com.fundraiser.backend.entity.UserAccount;
import com.fundraiser.backend.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateUserAccountControllerTest {

    @Mock
    private UserAccountRepository userAccountRepository;

    @InjectMocks
    private CreateUserAccountController createUserAccountController;

    private Map<String, String> validData;
    private Map<String, String> invalidData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Valid account data
        validData = new HashMap<>();
        validData.put("username", "johndoe");
        validData.put("email", "john@test.com");
        validData.put("password", "password123");
        validData.put("role", "FUND_RAISER");

        // Invalid account data - missing required fields
        invalidData = new HashMap<>();
        invalidData.put("username", "");
        invalidData.put("email", "");
        invalidData.put("password", "");
        invalidData.put("role", "");
    }

    @Test
    void validateAccount_validData_returnsTrue() {
        // Act
        boolean result = createUserAccountController.validateAccount(validData);

        // Assert
        assertTrue(result);
    }

    @Test
    void validateAccount_invalidData_returnsFalse() {
        // Act
        boolean result = createUserAccountController.validateAccount(invalidData);

        // Assert
        assertFalse(result);
    }

    @Test
    void createAccount_validData_returnsUserAccount() {
        // Arrange
        UserAccount mockAccount = new UserAccount();
        mockAccount.setUsername("johndoe");
        mockAccount.setEmail("john@test.com");
        mockAccount.setPassword("password123");
        mockAccount.setRole("FUND_RAISER");
        when(userAccountRepository.save(any(UserAccount.class)))
                .thenReturn(mockAccount);

        // Act
        UserAccount result = createUserAccountController.createAccount(validData);

        // Assert
        assertNotNull(result);
        assertEquals("johndoe", result.getUsername());
        assertEquals("john@test.com", result.getEmail());
        assertEquals("FUND_RAISER", result.getRole());
    }

    @Test
    void createAccount_invalidData_returnsNull() {
        // Act
        UserAccount result = createUserAccountController.createAccount(invalidData);

        // Assert
        assertNull(result);
    }
}