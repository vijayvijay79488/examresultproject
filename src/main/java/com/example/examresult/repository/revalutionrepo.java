package com.example.examresult.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.examresult.model.revalutionmodel;

public interface revalutionrepo extends JpaRepository<revalutionmodel, Integer> {
	List<revalutionmodel> findByStatus(String status);

}