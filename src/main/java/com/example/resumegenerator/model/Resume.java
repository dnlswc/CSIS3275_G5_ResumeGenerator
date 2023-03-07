package com.example.resumegenerator.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

@Entity
@Table(name = "resumes")
public class Resume {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private PersonalInfo personalInfo;

	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderBy("job_end_date DESC")
	private Set<Job> jobs = new HashSet<>();

	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderBy("edu_end_date DESC")
	private Set<Education> education = new HashSet<>();

	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderBy("skill ASC")
	private Set<MiscInfo> miscInfo = new HashSet<>();

	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "user_id", nullable = true)
	@JsonIgnore
	private User user;

	public Resume() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	public Set<Education> getEducation() {
		return education;
	}

	public void setEducation(Set<Education> education) {
		this.education = education;
	}

	public Set<MiscInfo> getMiscInfo() {
		return miscInfo;
	}

	public void setMiscInfo(Set<MiscInfo> miscInfo) {
		this.miscInfo = miscInfo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addPersonalInfo(PersonalInfo personalInfo) {
		setPersonalInfo(personalInfo);
		personalInfo.setResume(this);
	}

	public void addJob(Job job) {
		this.jobs.add(job);
		job.setResume(this);
	}

	public void addEducation(Education education) {
		this.education.add(education);
		education.setResume(this);
	}

	public void addMiscInfo(MiscInfo miscInfo) {
		this.miscInfo.add(miscInfo);
		miscInfo.setResume(this);
	}

}
