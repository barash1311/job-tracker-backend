package com.barash.jobtrackerbackend.dto;

import lombok.Data;

@Data
public class JobApplicationResponse {
    private Long id;
    private String companyName;
    private String role;
    private String url;
    private String status;
    private String notes;
    private String appliedDate;
    private String createdAt;
    private String updatedAt;
}
