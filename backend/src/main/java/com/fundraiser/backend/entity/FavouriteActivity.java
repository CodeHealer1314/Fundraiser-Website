package com.fundraiser.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "favourite_activities")
public class FavouriteActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String doneeId;

    @Column(nullable = false)
    private String activityId;
}