package com.fundraiser.backend.repository;

import com.fundraiser.backend.entity.FundRaisingActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundRaisingActivityRepository extends JpaRepository<FundRaisingActivity, Long> {

    List<FundRaisingActivity> findByFundRaiserIdAndCategoryAndStatusAndCreatedDateBetween(
            String fundRaiserId, String category, String status,
            String from, String to);

    List<FundRaisingActivity> findByFundRaiserId(String fundRaiserId);

    List<FundRaisingActivity> findByTitleContainingIgnoreCaseAndStatus(
            String keyword, String status);

    List<FundRaisingActivity> findByFundRaiserIdAndTitleContainingIgnoreCase(
            String fundRaiserId, String keyword);
}