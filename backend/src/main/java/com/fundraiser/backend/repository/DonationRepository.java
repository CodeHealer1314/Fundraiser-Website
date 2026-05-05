package com.fundraiser.backend.repository;

import com.fundraiser.backend.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    List<Donation> findByDoneeId(String doneeId);

    List<Donation> findByDoneeIdAndCategoryAndDonationDateBetween(
            String doneeId, String category,
            String dateFrom, String dateTo);
}