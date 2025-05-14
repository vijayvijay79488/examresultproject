package com.example.examresult.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.examresult.model.studentrevalutionRecords;
import com.example.examresult.repository.studentrevalutionrecordsrepo;

@Service
public class studentrevalutionservice {
	@Autowired
	private studentrevalutionrecordsrepo repo;

	public List<studentrevalutionRecords> gettall(String registered) {
		List<studentrevalutionRecords> one = repo.findByRegistered(registered);
		return one;
	}

//	public List<studentrevalutionRecords> getallrevaludtion() {
//		return repo.findAll();
//		// TODO Auto-generated method stub
////		return null;
//	}

}
