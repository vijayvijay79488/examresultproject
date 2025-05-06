package com.example.examresult.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.examresult.model.StudentResult;

public interface StudentResultRepository extends JpaRepository<StudentResult, Long> {
	List<StudentResult> findAll();
}
