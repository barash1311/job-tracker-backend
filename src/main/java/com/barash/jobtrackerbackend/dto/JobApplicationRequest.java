package com.barash.jobtrackerbackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobApplicationRequest {
    @NotNull(message = "Company name is required")
    private String companyName;
    @NotNull(message = "Role is required")
    private String role;
    @NotNull(message = "URL is required")
    private String url;
    private String notes;
    private String appliedDate;
    @NotNull(message = "Status is required")
    private String status;
}
