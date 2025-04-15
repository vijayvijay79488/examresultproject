package com.example.examresult.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.examresult.model.StudentInfoId;
import com.example.examresult.model.studentinfo;

@Repository
public interface studentinforepo extends JpaRepository<studentinfo, StudentInfoId> {

//	studentinfo findById(Long registered, String dob);
//	studentinfo findBySem(long id, String dob, int semester);

//	void save(addingstudentdetails_model std);
}
