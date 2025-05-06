package com.example.examresult.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.examresult.model.StudentResult;

public interface StudentResultRepository extends JpaRepository<StudentResult, Long> {
	List<StudentResult> findAll();

//	@Query("SELECT sr FROM StudentResult sr " + "JOIN sr.student s "
//			+ "WHERE sr.semester = :semester AND s.department = :department")
//	List<StudentResult> findBySemesterAndDepartment(String semester, String department);
	@Query("SELECT sr FROM StudentResult sr WHERE sr.semester = :semester AND sr.student.department = :department")
	List<StudentResult> findBySemesterAndDepartment(String semester, String department);
}
