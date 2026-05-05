package com.fundraiser.backend.repository;

import com.fundraiser.backend.entity.Donee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoneeRepository extends JpaRepository<Donee, Long> {
    Optional<Donee> findByEmail(String email);
}