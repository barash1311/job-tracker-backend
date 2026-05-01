package com.barash.jobtrackerbackend.controller;

import com.barash.jobtrackerbackend.dto.JobApplicationRequest;
import com.barash.jobtrackerbackend.dto.JobApplicationResponse;
import com.barash.jobtrackerbackend.model.Status;
import com.barash.jobtrackerbackend.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobApplicationController {
    private final JobApplicationService jobService;

    @GetMapping
    public ResponseEntity<List<JobApplicationResponse>> getAllJobs(
            @AuthenticationPrincipal UserDetails userDetails
    ){
        List<JobApplicationResponse> jobs=jobService.getAllJobs(userDetails.getUsername());
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<JobApplicationResponse>> filterJobs(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam Status status
    ){
        List<JobApplicationResponse> jobs=jobService.getJobByStatus(userDetails.getUsername(),status);
        return ResponseEntity.ok(jobs);
    }

    @PostMapping
    public ResponseEntity<JobApplicationResponse> createJobApplication(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody JobApplicationRequest request
    ){
      JobApplicationResponse created=jobService.createJob(
              userDetails.getUsername(),request);
      return ResponseEntity.status(HttpStatus.CREATED).body((created));
    }
    @PutMapping("{id}")
    public ResponseEntity<JobApplicationResponse> updateJob(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id,
            @Valid @RequestBody JobApplicationRequest request
    ){
        JobApplicationResponse updated=jobService.updateJob(
                userDetails.getUsername(),id,request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteJob(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id
    ){
        jobService.deleteJob(userDetails.getUsername(), id);
        return ResponseEntity.noContent().build();
    }
}
