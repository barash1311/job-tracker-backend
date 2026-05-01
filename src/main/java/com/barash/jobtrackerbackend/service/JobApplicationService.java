package com.barash.jobtrackerbackend.service;

import com.barash.jobtrackerbackend.dto.JobApplicationRequest;
import com.barash.jobtrackerbackend.dto.JobApplicationResponse;
import com.barash.jobtrackerbackend.model.JobApplication;
import com.barash.jobtrackerbackend.model.Status;
import com.barash.jobtrackerbackend.model.User;
import com.barash.jobtrackerbackend.repository.JobApplicationRepository;
import com.barash.jobtrackerbackend.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository repository;
    private final UserRepository userRepository;

    public List<JobApplicationResponse> getAllJobs(String username) {
        return repository.findByUserEmailOrderByCreatedAtDesc(username)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<JobApplicationResponse> getJobByStatus(String username, Status status) {
        return repository.findByUserEmailAndStatusOrderByCreatedAtDesc(username, status)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public JobApplicationResponse createJob(String username, @Valid JobApplicationRequest request) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        JobApplication job = JobApplication.builder()
                .companyName(request.getCompanyName())
                .jobRole(request.getRole())
                .jobUrl(request.getUrl())
                .notes(request.getNotes())
                .appliedDate(request.getAppliedDate() != null ? LocalDate.parse(request.getAppliedDate()) : null)
                .status(Status.valueOf(request.getStatus()))
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();
        return mapToResponse(repository.save(job));
    }

    public JobApplicationResponse updateJob(String username, Long id, @Valid JobApplicationRequest request) {
        JobApplication job = repository.findByIdAndUserEmail(id, username).orElseThrow(() -> new RuntimeException("Job not found"));
        job.setCompanyName(request.getCompanyName());
        job.setJobRole(request.getRole());
        job.setJobUrl(request.getUrl());
        job.setNotes(request.getNotes());
        job.setAppliedDate(request.getAppliedDate() != null ? LocalDate.parse(request.getAppliedDate()) : null);
        job.setStatus(Status.valueOf(request.getStatus()));
        job.setUpdatedAt(LocalDateTime.now());
        return mapToResponse(repository.save(job));
    }

    public void deleteJob(String username, Long id) {
        JobApplication job = repository.findByIdAndUserEmail(id, username).orElseThrow(() -> new RuntimeException("Job not found"));
        repository.delete(job);
    }

    private JobApplicationResponse mapToResponse(JobApplication job) {
        JobApplicationResponse response = new JobApplicationResponse();
        response.setId(job.getId());
        response.setCompanyName(job.getCompanyName());
        response.setRole(job.getJobRole());
        response.setUrl(job.getJobUrl());
        response.setStatus(job.getStatus().name());
        response.setNotes(job.getNotes());
        response.setAppliedDate(job.getAppliedDate() != null ? job.getAppliedDate().toString() : null);
        response.setCreatedAt(job.getCreatedAt() != null ? job.getCreatedAt().toString() : null);
        response.setUpdatedAt(job.getUpdatedAt() != null ? job.getUpdatedAt().toString() : null);
        return response;
    }
}
