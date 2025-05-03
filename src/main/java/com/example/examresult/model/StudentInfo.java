package com.example.examresult.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "studentinfo")
public class StudentInfo {

	@EmbeddedId
	private StudentInfoId id;

	private String tamil;
	private String english;
//	private String result;

	public StudentInfoId getId() {
		return id;
	}

	public void setId(StudentInfoId id) {
		this.id = id;
	}

	public String getTamil() {
		return tamil;
	}

	public void setTamil(String tamil) {
		this.tamil = tamil;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getMaths() {
		return maths;
	}

	public void setMaths(String maths) {
		this.maths = maths;
	}

	public String getScience() {
		return science;
	}

	public void setScience(String science) {
		this.science = science;
	}

	public String getSocial() {
		return social;
	}

	public void setSocial(String social) {
		this.social = social;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	private String maths;
	private String science;
	private String social;
	private String result;
	private String grade;

	// Getters and setters
}
