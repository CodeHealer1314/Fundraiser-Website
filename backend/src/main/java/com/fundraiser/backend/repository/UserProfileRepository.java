package com.fundraiser.backend.repository;

import com.fundraiser.backend.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

    // Search profiles by keyword matching fullname, username, or email
    @Query("SELECT p FROM UserProfile p WHERE " +
            "LOWER(p.fullname) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.username) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<UserProfile> searchProfiles(@Param("keyword") String keyword);
}
