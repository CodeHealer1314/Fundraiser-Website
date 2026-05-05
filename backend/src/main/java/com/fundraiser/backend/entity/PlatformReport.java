package com.fundraiser.backend.entity;

import lombok.Data;

import java.util.Map;

@Data
public class PlatformReport {

    private String reportType;
    private String dateRange;
    private int totalActivities;
    private int totalDonations;
    private double totalAmount;
    private Map<String, Object> reportData;
}