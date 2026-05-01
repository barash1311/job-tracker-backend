package com.barash.jobtrackerbackend.repository;

import com.barash.jobtrackerbackend.model.JobApplication;
import com.barash.jobtrackerbackend.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface JobApplicationRepository extends JpaRepository<JobApplication,Long> {
    List<JobApplication> findByUserEmailOrderByCreatedAtDesc(String email);
    List<JobApplication> findByUserEmailAndStatusOrderByCreatedAtDesc(String email, Status status);
    Optional<JobApplication> findByIdAndUserEmail(Long id, String email);
}
