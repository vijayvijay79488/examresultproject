package com.example.examresult.dto;

public class SubjectAnalyticsDTO {
	private String subject;

	public SubjectAnalyticsDTO(String subject, int passCount, int failCount) {
		super();
		this.subject = subject;
		this.passCount = passCount;
		this.failCount = failCount;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getPassCount() {
		return passCount;
	}

	public void setPassCount(int passCount) {
		this.passCount = passCount;
	}

	public int getFailCount() {
		return failCount;
	}

	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}

	private int passCount;
	private int failCount;

	// Constructor, getters, setters
}
