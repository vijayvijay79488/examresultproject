package com.example.examresult.dto;

import java.util.List;

public class SemesterAnalyticsDTO {
	private int semester;

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public List<DepartmentAnalyticsDTO> getDepartments() {
		return departments;
	}

	public void setDepartments(List<DepartmentAnalyticsDTO> departments) {
		this.departments = departments;
	}

	private List<DepartmentAnalyticsDTO> departments;

	public SemesterAnalyticsDTO(int semester, List<DepartmentAnalyticsDTO> departments) {
		this.semester = semester;
		this.departments = departments;
	}

	// Getters and setters
}
