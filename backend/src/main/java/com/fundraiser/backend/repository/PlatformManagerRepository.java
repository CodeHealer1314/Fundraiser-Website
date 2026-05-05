package com.fundraiser.backend.repository;

import com.fundraiser.backend.entity.PlatformManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlatformManagerRepository extends JpaRepository<PlatformManager, Long> {
    Optional<PlatformManager> findByEmail(String email);
}