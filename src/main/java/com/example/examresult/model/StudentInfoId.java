package com.example.examresult.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class StudentInfoId implements Serializable {
	private String registered;

//	public StudentInfoId(String registered2, String semester2) {
//		// TODO Auto-generated constructor stub
//	}

//	public StudentInfoId(String registered2, String semester2) {
//		// TODO Auto-generated constructor stub
//	}
	public StudentInfoId() {
	}

	// Proper constructor to set values
	public StudentInfoId(String registered, String semester) {
		this.registered = registered;
		this.semester = semester;
	}

	public String getRegistered() {
		return registered;
	}

	public void setRegistered(String registered) {
		this.registered = registered;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	private String semester;

	// Getters, setters, equals, and hashCode
}