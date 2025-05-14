package com.example.examresult.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.examresult.model.studentrevalutionRecords;
import com.example.examresult.service.studentrevalutionservice;

@CrossOrigin
@RestController
@RequestMapping("/studentrevalution")
public class studentrevalutioncontroller {
	@Autowired
	private studentrevalutionservice service;

	@GetMapping("/gettall")
	public ResponseEntity<Map<String, Object>> getall(@Param("registered") String registered) {
		Map<String, Object> res = new HashMap<>();
		try {
			List<studentrevalutionRecords> response = service.gettall(registered);
			res.put("status", HttpStatus.OK);
			res.put("response", response);
		} catch (Exception e) {
			res.put("status", HttpStatus.BAD_REQUEST);
			res.put("message", e.getMessage());
		}
		return new ResponseEntity<Map<String, Object>>(res, (HttpStatusCode) res.get("status"));
	}
}
