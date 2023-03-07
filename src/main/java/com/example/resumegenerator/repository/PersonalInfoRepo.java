package com.example.resumegenerator.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resumegenerator.model.PersonalInfo;

public interface PersonalInfoRepo extends JpaRepository<PersonalInfo, Long> {

//	Optional<PersonalInfo> findById(Long personalInfoId);
	
//	List<PersonalInfo> findByResumeId(Long resumeId);
	
}