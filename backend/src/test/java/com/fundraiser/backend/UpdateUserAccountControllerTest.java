package com.fundraiser.backend;

import com.fundraiser.backend.controller.UpdateUserAccountController;
import com.fundraiser.backend.entity.UserAccount;
import com.fundraiser.backend.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateUserAccountControllerTest {

    @Mock
    private UserAccountRepository userAccountRepository;

    @InjectMocks
    private UpdateUserAccountController updateUserAccountController;

    private UserAccount mockAccount;
    private Map<String, String> validData;
    private Map<String, String> invalidData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockAccount = new UserAccount();
        mockAccount.setAccountId("acc-001");
        mockAccount.setUsername("johndoe");
        mockAccount.setEmail("john@test.com");
        mockAccount.setRole("FUND_RAISER");

        validData = new HashMap<>();
        validData.put("username", "johnupdated");
        validData.put("email", "johnupdated@test.com");
        validData.put("role", "DONEE");

        invalidData = new HashMap<>();
        invalidData.put("username", "");
        invalidData.put("email", "");
        invalidData.put("role", "");
    }

    @Test
    void loadAccount_accountExists_returnsAccount() {
        // Arrange
        when(userAccountRepository.findById("acc-001"))
                .thenReturn(Optional.of(mockAccount));

        // Act
        UserAccount result = updateUserAccountController.loadAccount("acc-001");

        // Assert
        assertNotNull(result);
        assertEquals("acc-001", result.getAccountId());
        assertEquals("johndoe", result.getUsername());
    }

    @Test
    void loadAccount_accountNotFound_returnsNull() {
        // Arrange
        when(userAccountRepository.findById("unknown-id"))
                .thenReturn(Optional.empty());

        // Act
        UserAccount result = updateUserAccountController.loadAccount("unknown-id");

        // Assert
        assertNull(result);
    }

    @Test
    void updateAccount_validData_returnsTrue() {
        // Arrange
        when(userAccountRepository.findById("acc-001"))
                .thenReturn(Optional.of(mockAccount));
        when(userAccountRepository.save(any(UserAccount.class)))
                .thenReturn(mockAccount);

        // Act
        boolean result = updateUserAccountController.updateAccount("acc-001", validData);

        // Assert
        assertTrue(result);
        verify(userAccountRepository, times(1)).save(any(UserAccount.class));
    }

    @Test
    void updateAccount_accountNotFound_returnsFalse() {
        // Arrange
        when(userAccountRepository.findById("unknown-id"))
                .thenReturn(Optional.empty());

        // Act
        boolean result = updateUserAccountController.updateAccount("unknown-id", validData);

        // Assert
        assertFalse(result);
    }

    @Test
    void updateAccount_invalidData_returnsFalse() {
        // Arrange
        when(userAccountRepository.findById("acc-001"))
                .thenReturn(Optional.of(mockAccount));

        // Act
        boolean result = updateUserAccountController.updateAccount("acc-001", invalidData);

        // Assert
        assertFalse(result);
    }
}
