package com.example.examresult.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.examresult.model.revalutionmodel;

public interface revalutionrepo extends JpaRepository<revalutionmodel, Integer> {
	revalutionmodel findByRegisteredAndSemester(int registered, int semester);

}