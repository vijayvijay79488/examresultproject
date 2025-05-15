package com.example.examresult.dto;

import java.util.List;

public class StudentResponseDTO {

	private String registerNumber;
	private String semester;
	private List<SubjectDTO> subjects;

	public StudentResponseDTO(String registerNumber, String semester, List<SubjectDTO> subjects) {
		this.registerNumber = registerNumber;
		this.semester = semester;
		this.subjects = subjects;
	}

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public List<SubjectDTO> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectDTO> subjects) {
		this.subjects = subjects;
	}
}