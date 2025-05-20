package com.example.examresult.dto;

import com.example.examresult.model.selectsem;

public class requestbody {
	private String name;
	private selectsem sem;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public selectsem getSem() {
		return sem;
	}

	public void setSem(selectsem sem) {
		this.sem = sem;
	}
}
