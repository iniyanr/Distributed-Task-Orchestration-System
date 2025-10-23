package com.iniyan.task_orchestrator.repository;

import com.iniyan.task_orchestrator.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
