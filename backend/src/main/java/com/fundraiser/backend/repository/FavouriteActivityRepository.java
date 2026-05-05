package com.fundraiser.backend.repository;

import com.fundraiser.backend.entity.FavouriteActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteActivityRepository extends JpaRepository<FavouriteActivity, Long> {

    List<FavouriteActivity> findByDoneeId(String doneeId);

    List<FavouriteActivity> findByDoneeIdAndActivityIdContainingIgnoreCase(
            String doneeId, String keyword);
}