package com.fundraiser.backend.repository;

import com.fundraiser.backend.entity.FundRaiser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FundRaiserRepository extends JpaRepository<FundRaiser, Long> {
    Optional<FundRaiser> findByEmail(String email);
}
