package com.fundraiser.backend.repository;

import com.fundraiser.backend.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

    Optional<UserAccount> findByUsername(String username);

    Optional<UserAccount> findByEmail(String email);

    @Query("SELECT a FROM UserAccount a WHERE " +
            "LOWER(a.username) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(a.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(a.role) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<UserAccount> searchAccounts(@Param("keyword") String keyword);
}
