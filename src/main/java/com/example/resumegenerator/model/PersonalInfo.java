package com.example.resumegenerator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "personal_info")
public class PersonalInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "resume_email")
	private String resumeEmail;

	@Column(name = "phone")
	private String phone;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "resume_id", nullable = false)
	@JsonIgnore
	private Resume resume;

	public PersonalInfo() {
	}

	public PersonalInfo(String fullName, String resumeEmail, String phone) {
		this.fullName = fullName;
		this.resumeEmail = resumeEmail;
		this.phone = phone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getResumeEmail() {
		return resumeEmail;
	}

	public void setResumeEmail(String resumeEmail) {
		this.resumeEmail = resumeEmail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

}