package com.example.resumegenerator.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resumegenerator.model.MiscInfo;

public interface MiscInfoRepo extends JpaRepository<MiscInfo, Long> {

//	Optional<MiscInfo> findById(Long miscInfoId);
	
//	List<MiscInfo> findByResumeId(Long resumeId);
	
}