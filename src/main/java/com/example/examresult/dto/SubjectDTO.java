package com.example.examresult.dto;

public class SubjectDTO {
	private String name;
	private int mark;
	private boolean isRevaluationApplied;

	// Constructor
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public boolean isRevaluationApplied() {
		return isRevaluationApplied;
	}

	public void setRevaluationApplied(boolean isRevaluationApplied) {
		this.isRevaluationApplied = isRevaluationApplied;
	}

	public SubjectDTO(String name, int mark, boolean isRevaluationApplied) {
		this.name = name;
		this.mark = mark;
		this.isRevaluationApplied = isRevaluationApplied;
	}

}