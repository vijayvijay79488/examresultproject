package com.example.examresult.dto;

import java.util.List;

public class DepartmentAnalyticsDTO {
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}

	public List<TopStudentDTO> getTopStudents() {
		return topStudents;
	}

	public void setTopStudents(List<TopStudentDTO> topStudents) {
		this.topStudents = topStudents;
	}

	public List<SubjectAnalyticsDTO> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectAnalyticsDTO> subjects) {
		this.subjects = subjects;
	}

	private String department;
	private int studentCount;
	private List<TopStudentDTO> topStudents;
	private List<SubjectAnalyticsDTO> subjects;

	public DepartmentAnalyticsDTO(String department, int studentCount, List<TopStudentDTO> topStudents,
			List<SubjectAnalyticsDTO> subjects) {
		this.department = department;
		this.studentCount = studentCount;
		this.topStudents = topStudents;
		this.subjects = subjects;
	}

	// Getters and setters
}
