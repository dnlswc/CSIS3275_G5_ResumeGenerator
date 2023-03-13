package com.example.resumegenerator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resumegenerator.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

//	Boolean existsByUsername(String username);
	
}