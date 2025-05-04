package com.example.examresult.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.examresult.model.studentinfo_model;

@Repository
public interface studentinforepo extends JpaRepository<studentinfo_model, String> {
	List<studentinfo_model> findByRegistered(String registered);
}
