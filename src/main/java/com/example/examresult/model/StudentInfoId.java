package com.example.examresult.model;

//StudentInfoId.java
//package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class StudentInfoId implements Serializable {

	private Long registered;
	private Integer semester;
	private String dob;

	// ✅ Required by JPA
	public StudentInfoId() {
	}

	public StudentInfoId(Long registered, Integer semester, String dob) {
		this.registered = registered;
		this.semester = semester;
		this.dob = dob;
	}

	// Getters and Setters...

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof StudentInfoId))
			return false;
		StudentInfoId that = (StudentInfoId) o;
		return Objects.equals(registered, that.registered) && Objects.equals(semester, that.semester)
				&& Objects.equals(dob, that.dob);
	}

	@Override
	public int hashCode() {
		return Objects.hash(registered, semester, dob);
	}
}
