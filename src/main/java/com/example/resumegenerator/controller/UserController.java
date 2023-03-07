package com.example.resumegenerator.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.resumegenerator.model.Education;
import com.example.resumegenerator.model.Job;
import com.example.resumegenerator.model.MiscInfo;
import com.example.resumegenerator.model.Resume;
import com.example.resumegenerator.model.User;
import com.example.resumegenerator.repository.ResumeRepo;
import com.example.resumegenerator.repository.UserRepo;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ResumeRepo resumeRepo;
	
	// to get all users
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		try {
			List<User> users = new ArrayList<User>();
			userRepo.findAll().forEach(users::add);
			
			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(users, HttpStatus.OK);
					
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// to get a user by username
	@GetMapping("/user/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
		try {
			Optional<User> user = userRepo.findByUsername(username);
			
			if (user.isPresent()) {
				return new ResponseEntity<>(user.get(), HttpStatus.OK);
			}
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// to get the resumes of a user
	@GetMapping("/user/{username}/resume")
	public ResponseEntity<List<Resume>> getResumesbyUsername(@PathVariable String username) {
		try {
			Optional<User> userData = userRepo.findByUsername(username);
			List<Resume> resumes = new ArrayList<>();
			
			if (userData.isPresent()) {
				User user = userData.get();
				for (Resume resume : user.getResumes()) {
					resumes.add(resume);
				}
				return new ResponseEntity<>(resumes, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// to create a resume for a user
	@PostMapping("/user/{username}/resume")
	public ResponseEntity<Resume> addResumeForUser(@PathVariable String username, @RequestBody Resume resume) {
		try {
			Optional<User> userToAddResume = userRepo.findByUsername(username);
			
			if (userToAddResume.isPresent()) {
				Resume newResume = new Resume();
				newResume.addPersonalInfo(resume.getPersonalInfo());
				for (Job job : resume.getJobs()) {
					newResume.addJob(job);
				}
				for (Education education : resume.getEducation()) {
					newResume.addEducation(education);
				}
				for (MiscInfo miscInfo : resume.getMiscInfo()) {
					newResume.addMiscInfo(miscInfo);
				}
				userToAddResume.get().addResume(newResume);
				resumeRepo.save(newResume);
				
				return new ResponseEntity<>(newResume, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/user/{username}/resume/{resumeId}")
	public ResponseEntity<Resume> updateResumeForUser(
			@PathVariable("username") String username,
			@PathVariable("resumeId") Long resumeId,
			@RequestBody Resume resume) {
		try {
			Optional<User> userToUpdateResume = userRepo.findByUsername(username);
			Optional<Resume> resumeToUpdate = resumeRepo.findById(resumeId);
			
			if (userToUpdateResume.isPresent() && resumeToUpdate.isPresent()) {
//				resumeToUpdate.get().setPersonalInfo(resume.getPersonalInfo());
//				resumeToUpdate.get().setJobs(resume.getJobs());
//				resumeToUpdate.get().setEducation(resume.getEducation());
//				resumeToUpdate.get().setMiscInfo(resume.getMiscInfo());
//				userToUpdateResume.get().setResumes(null);
//				resumeRepo.save(resumeToUpdate.get());
				
				return new ResponseEntity<>(resumeToUpdate.get(), HttpStatus.OK);
			}
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
}
