package com.fundraiser.backend.repository;

import com.fundraiser.backend.entity.FundRaisingActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundRaisingActivityRepository extends JpaRepository<FundRaisingActivity, Long> {
    List<FundRaisingActivity> findByFundRaiserId(String fundRaiserId);
    List<FundRaisingActivity> findByFundRaiserIdAndTitleContainingIgnoreCase(String fundRaiserId, String keyword);
    List<FundRaisingActivity> findByFundRaiserIdAndStatus(String fundRaiserId, String status);
    List<FundRaisingActivity> findByFundRaiserIdAndCategoryAndStatusAndCreatedDateBetween(
            String fundRaiserId, String category, String status, String from, String to);
}