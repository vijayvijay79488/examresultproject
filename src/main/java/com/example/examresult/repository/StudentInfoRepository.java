package com.example.examresult.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.examresult.model.StudentInfoId;

//StudentInfoRepository.java
//package com.example.demo.repository;

//import com.example.demo.entity.StudentInfo;
//import com.example.demo.entity.StudentInfoId;
import com.example.examresult.model.studentinfo;

@Repository
public interface StudentInfoRepository extends JpaRepository<studentinfo, StudentInfoId> {

	// Custom method to find by both keys
//	studentinfo findByIdRegisteredAndIdSemester(Long registered, Integer semester);
//
//	List<studentinfo> findAllByIdRegistered(Long registered); // if you want to get all semesters for one registered
}
