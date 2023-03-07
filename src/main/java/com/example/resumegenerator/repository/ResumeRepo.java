package com.example.resumegenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resumegenerator.model.Resume;

public interface ResumeRepo extends JpaRepository<Resume, Long> {

//	Optional<Resume> findById(Long personalInfoId);
	
//	List<Resume> findByUsername(String username);
	
}