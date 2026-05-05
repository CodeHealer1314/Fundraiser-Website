package com.fundraiser.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String donationId;

    @Column(nullable = false)
    private String doneeId;

    @Column(nullable = false)
    private String activityId;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Date donationDate;
}