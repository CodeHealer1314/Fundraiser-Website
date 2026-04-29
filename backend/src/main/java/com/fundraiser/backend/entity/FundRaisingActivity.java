package com.fundraiser.backend.entity;

import com.fundraiser.backend.repository.FundRaisingActivityRepository;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "fund_raising_activities")
public class FundRaisingActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private double goalAmount;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String fundRaiserId;

    @Column(nullable = false)
    private int viewCount;

    @Column(nullable = false)
    private int shortlistCount;

    // Empty implementation - will implement in Step 4
    public static FundRaisingActivity saveActivity(
            Object data,
            FundRaisingActivityRepository repository) {
        return null;
    }
}