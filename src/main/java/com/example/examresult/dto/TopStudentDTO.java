package com.example.examresult.dto;

public class TopStudentDTO {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	private String registerNumber;
	private int totalMarks;
	private String avatarUrl;

	public TopStudentDTO(String name, String registerNumber, int totalMarks, String avatarUrl) {
		this.name = name;
		this.registerNumber = registerNumber;
		this.totalMarks = totalMarks;
		this.avatarUrl = avatarUrl;
	}

	public TopStudentDTO() {

	}

	// Getters and setters
}
