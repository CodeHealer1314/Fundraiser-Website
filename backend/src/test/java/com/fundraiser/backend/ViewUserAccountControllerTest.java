package com.fundraiser.backend;

import com.fundraiser.backend.controller.ViewUserAccountController;
import com.fundraiser.backend.entity.UserAccount;
import com.fundraiser.backend.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ViewUserAccountControllerTest {

    @Mock
    private UserAccountRepository userAccountRepository;

    @InjectMocks
    private ViewUserAccountController viewUserAccountController;

    private List<UserAccount> mockAccounts;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockAccounts = new ArrayList<>();

        UserAccount account1 = new UserAccount();
        account1.setAccountId("acc-001");
        account1.setUsername("johndoe");
        account1.setEmail("john@test.com");
        account1.setRole("FUND_RAISER");

        UserAccount account2 = new UserAccount();
        account2.setAccountId("acc-002");
        account2.setUsername("janesmith");
        account2.setEmail("jane@test.com");
        account2.setRole("DONEE");

        mockAccounts.add(account1);
        mockAccounts.add(account2);
    }

    @Test
    void loadAccounts_accountsExist_returnsList() {
        // Arrange
        when(userAccountRepository.findAll())
                .thenReturn(mockAccounts);

        // Act
        List<UserAccount> result = viewUserAccountController.loadAccounts();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("johndoe", result.get(0).getUsername());
        assertEquals("janesmith", result.get(1).getUsername());
    }

    @Test
    void loadAccounts_noAccounts_returnsEmptyList() {
        // Arrange
        when(userAccountRepository.findAll())
                .thenReturn(new ArrayList<>());

        // Act
        List<UserAccount> result = viewUserAccountController.loadAccounts();

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }
}