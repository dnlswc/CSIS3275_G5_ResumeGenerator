package com.example.resumegenerator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "jobs")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "company")
	private String company;

	@Column(name = "position")
	private String position;

	@Column(name = "job_start_date")
	private String jobStartDate;

	@Column(name = "job_end_date")
	private String jobEndDate;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "resume_id", nullable = false)
	@JsonIgnore
	private Resume resume;

	public Job() {
	}

	public Job(String company, String position, String jobStartDate, String jobEndDate) {
		this.company = company;
		this.position = position;
		this.jobStartDate = jobStartDate;
		this.jobEndDate = jobEndDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getJobStartDate() {
		return jobStartDate;
	}

	public void setJobStartDate(String jobStartDate) {
		this.jobStartDate = jobStartDate;
	}

	public String getJobEndDate() {
		return jobEndDate;
	}

	public void setJobEndDate(String jobEndDate) {
		this.jobEndDate = jobEndDate;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

}
