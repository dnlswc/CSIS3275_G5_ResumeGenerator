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
@Table(name = "education")
public class Education {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "degree")
	private String degree;

	@Column(name = "institution")
	private String institution;

	@Column(name = "grad_year")
	private String gradYear;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "resume_id", nullable = false)
	@JsonIgnore
	private Resume resume;

	public Education() {
	}

	public Education(String degree, String institution, String gradYear) {
		this.degree = degree;
		this.institution = institution;
		this.gradYear = gradYear;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getGradYear() {
		return gradYear;
	}

	public void setGradYear(String gradYear) {
		this.gradYear = gradYear;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

}
