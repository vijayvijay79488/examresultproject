package com.example.examresult.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.examresult.model.studentresult_model;

@Repository
public interface checkregandsem extends JpaRepository<studentresult_model, Long> {
	studentresult_model findByRegisteredAndSemester(String registered, String semester);
}
