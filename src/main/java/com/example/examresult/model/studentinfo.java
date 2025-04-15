package com.example.examresult.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "studentinfo")
public class studentinfo {

	@EmbeddedId
	private StudentInfoId id;

	public StudentInfoId getId() {
		return id;
	}

	public void setId(StudentInfoId id) {
		this.id = id;
	}

	@Column(name = "name")
	private String name;
	@Column(name = "degree")
	private String degree;
	@Column(name = "tamil")
	private String tamil;
	@Column(name = "english")
	private String english;
	@Column(name = "maths")
	private String maths;
	@Column(name = "science")
	private String science;
	@Column(name = "social")
	private String social;
	@Column(name = "grade")
	private String grade;
	@Column(name = "result")
	private String result;

//	public long getRegistered() {
//		return registered;
//	}
//
//	public void setRegistered(long registered) {
//		this.registered = registered;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
