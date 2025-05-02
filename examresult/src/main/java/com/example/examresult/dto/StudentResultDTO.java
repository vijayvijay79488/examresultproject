package com.example.examresult.dto;

public class StudentResultDTO {
	private String tamil;
	private String english;
	private String maths;
	private String science;
	private String social;
	private String grade;
	private String result;

	public StudentResultDTO() {
	}

	public StudentResultDTO(String tamil, String english, String maths, String science, String social, String grade,
			String result) {
		this.tamil = tamil;
		this.english = english;
		this.maths = maths;
		this.science = science;
		this.social = social;
		this.grade = grade;
		this.result = result;
	}

	// Getters and Setters
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
