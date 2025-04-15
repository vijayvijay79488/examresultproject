package com.example.examresult.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.examresult.model.adminregister_model;

public interface adminregisterrepo extends JpaRepository<adminregister_model, Long> {
	adminregister_model findByEmail(String email);
}
