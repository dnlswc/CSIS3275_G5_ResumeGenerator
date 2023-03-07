package com.example.resumegenerator.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resumegenerator.model.Job;

public interface JobRepo extends JpaRepository<Job, Long> {

//	Optional<Job> findById(Long jobId);
	
//	List<Job> findByResumeId(Long resumeId);
	
}