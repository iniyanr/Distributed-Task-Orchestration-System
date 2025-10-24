package com.iniyan.task_orchestrator.controller;

import com.iniyan.task_orchestrator.dto.ApiResponse;
import com.iniyan.task_orchestrator.entity.Job;
import com.iniyan.task_orchestrator.exception.ResourceNotFoundException;
import com.iniyan.task_orchestrator.repository.JobRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping
    public ApiResponse<List<Job>> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return ApiResponse.success(jobs, "Jobs fetched successfully");
    }

    @GetMapping("/{id}")
    public ApiResponse<Job> getJobById(@PathVariable Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id " + id));
        return ApiResponse.success(job, "Job fetched successfully");
    }

    @PostMapping
    public ApiResponse<Job> createJob(@RequestBody Job job) {
        Job savedJob = jobRepository.save(job);
        return ApiResponse.success(savedJob, "Job created successfully");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteJob(@PathVariable Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id " + id));
        jobRepository.delete(job);
        return ApiResponse.success(null, "Job deleted successfully");
    }
}
