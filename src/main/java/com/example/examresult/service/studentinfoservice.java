package com.example.examresult.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.examresult.dto.StudentResultDTO;
import com.example.examresult.model.StudentInfoId;
import com.example.examresult.model.studentinfo;
import com.example.examresult.repository.studentinforepo;

class manual extends Exception {
	public manual(String msg) {
		super(msg);
	}
}

@Service
public class studentinfoservice {
	@Autowired
	private studentinforepo studentinforepo;

	public String addstd(Long registered, Integer semester, String dob, studentinfo stdinfo) throws manual {
		// Construct the composite key
		StudentInfoId id = new StudentInfoId(registered, semester, dob);
		stdinfo.setId(id);

		// Check if already exists
		Optional<studentinfo> existing = studentinforepo.findById(id);
		if (existing.isPresent()) {
			throw new manual("Student already exists");
		}

		// Save new record
		studentinforepo.save(stdinfo);
		return "Successfully added";
	}

	public Object getStudentInfo(Long registered, Integer semester, String dob) throws manual {
//		StudentInfoId id = new StudentInfoId(registered, semester, dob);
		StudentInfoId id = new StudentInfoId(registered, semester, dob);
		Optional<studentinfo> studentOpt = studentinforepo.findById(id);
		if (studentOpt.isPresent()) {
			studentinfo std = studentOpt.get();
			return new StudentResultDTO(std.getTamil(), std.getEnglish(), std.getMaths(), std.getScience(),
					std.getSocial(), std.getGrade(), std.getResult());
		} else {
			throw new manual("no id found");
		}
	}

	public Object getresult(Long registered, String dob) throws manual {
		StudentInfoId id = new StudentInfoId(registered, 0, dob);
		Optional<studentinfo> studentOpt = studentinforepo.findById(id);
		if (studentOpt.isPresent()) {
			studentinfo std = studentOpt.get();
			return new StudentResultDTO(std.getTamil(), std.getEnglish(), std.getMaths(), std.getScience(),
					std.getSocial(), std.getGrade(), std.getResult());
		} else {
			throw new manual("no data found");
		}
	}
}
