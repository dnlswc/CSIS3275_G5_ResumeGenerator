package com.example.resumegenerator.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resumegenerator.model.Education;

public interface EducationRepo extends JpaRepository<Education, Long> {

//	Optional<Education> findById(Long educationId);
	
//	List<Education> findByResumeId(Long resumeId);
	
}