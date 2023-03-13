package com.example.resumegenerator.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	@GetMapping("/user")
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
	
	// to get a user by email
	@GetMapping("/user/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
		try {
			Optional<User> user = userRepo.findByEmail(email);
			
			if (user.isPresent()) {
				return new ResponseEntity<>(user.get(), HttpStatus.OK);
			}
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// to get all resumes of a user
	@GetMapping("/user/{email}/resume")
	public ResponseEntity<List<Resume>> getResumesbyEmail(@PathVariable String email) {
		try {
			Optional<User> userData = userRepo.findByEmail(email);
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
	
	// to get a resume of a user
	@GetMapping("/user/{email}/resume/{resumeId}")
	public ResponseEntity<Resume> getResumesbyEmail(
			@PathVariable("email") String email,
			@PathVariable("resumeId") Long resumeId) {
		try {
			Optional<User> userData = userRepo.findByEmail(email);
			Optional<Resume> resumeData = resumeRepo.findById(resumeId);
			
			if (userData.isPresent() && resumeData.isPresent()) {
				if (userData.get().getId() == resumeData.get().getUser().getId()) {
					return new ResponseEntity<>(resumeData.get(), HttpStatus.OK);
				}
			}
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// to create a resume for a user
	@PostMapping("/user/{email}/resume")
	public ResponseEntity<Resume> addResumeForUser(@PathVariable String email, @RequestBody Resume resume) {
		try {
			Optional<User> userToAddResume = userRepo.findByEmail(email);
			
			if (userToAddResume.isPresent()) {
				Resume newResume = new Resume();
				
				newResume.addPersonalInfo(resume.getPersonalInfo());
				for (Education education : resume.getEducation()) {
					newResume.addEducation(education);
				}				
				for (Job job : resume.getJobs()) {
					newResume.addJob(job);
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
	
	// to update a resume for a user
	// will delete the entire resume & create a new one
	@PutMapping("/user/{email}/resume/{resumeId}")
	public ResponseEntity<Resume> updateResumeForUser(
			@PathVariable("email") String email,
			@PathVariable("resumeId") Long resumeId,
			@RequestBody Resume resume) {
		try {
			Optional<User> userToUpdateResume = userRepo.findByEmail(email);
			Optional<Resume> resumeToUpdate = resumeRepo.findById(resumeId);
			
			if (userToUpdateResume.isPresent() && resumeToUpdate.isPresent()) {
				if (userToUpdateResume.get().getId() == resumeToUpdate.get().getUser().getId()) {
					resumeRepo.deleteById(resumeId);
					
					Resume newResume = new Resume();
					
					newResume.addPersonalInfo(resume.getPersonalInfo());
					for (Education education : resume.getEducation()) {
						newResume.addEducation(education);
					}				
					for (Job job : resume.getJobs()) {
						newResume.addJob(job);
					}
					for (MiscInfo miscInfo : resume.getMiscInfo()) {
						newResume.addMiscInfo(miscInfo);
					}
					
					userToUpdateResume.get().addResume(newResume);
					resumeRepo.save(newResume);
					
					return new ResponseEntity<>(newResume, HttpStatus.OK);
				}
			}
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// to delete all resumes of a user
	@DeleteMapping("/user/{email}/resume")
	public ResponseEntity<Resume> deleteAllResumesOfUser(@PathVariable String email) {
		try {
			Optional<User> userToDeleteResume = userRepo.findByEmail(email);
						
			if (userToDeleteResume.isPresent()) {
				List<Resume> resumes = new ArrayList<Resume>();
				
				for (Resume resume : resumeRepo.findAll()) {
					if (resume.getUser().getId() == userToDeleteResume.get().getId()) {
						resumes.add(resume);
					}
				}
				
				if (resumes.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				
				for (Resume resume : resumes) {
					resumeRepo.deleteById(resume.getId());
				}
				
				return new ResponseEntity<>(HttpStatus.OK);
			}
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// to delete a resume of a user
	@DeleteMapping("/user/{email}/resume/{resumeId}")
	public ResponseEntity<Resume> deleteResumeOfUser(
			@PathVariable("email") String email,
			@PathVariable("resumeId") Long resumeId) {
		try {
			Optional<User> userToDeleteResume = userRepo.findByEmail(email);
			Optional<Resume> resumeToDelete = resumeRepo.findById(resumeId);
						
			if (userToDeleteResume.isPresent() && resumeToDelete.isPresent()) {
				if (userToDeleteResume.get().getId() == resumeToDelete.get().getUser().getId()) {
					resumeRepo.deleteById(resumeId);
					return new ResponseEntity<>(HttpStatus.OK);
				}
			}
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
