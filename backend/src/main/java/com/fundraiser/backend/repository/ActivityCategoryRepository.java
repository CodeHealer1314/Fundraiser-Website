package com.fundraiser.backend.repository;

import com.fundraiser.backend.entity.ActivityCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActivityCategoryRepository extends JpaRepository<ActivityCategory, Long> {

    Optional<ActivityCategory> findByName(String name);

    List<ActivityCategory> findByNameContainingIgnoreCase(String keyword);
}