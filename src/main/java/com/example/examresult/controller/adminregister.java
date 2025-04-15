package com.example.examresult.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.examresult.model.adminregister_model;
import com.example.examresult.service.adminregisterservice;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/adminregister")
public class adminregister {
	@Autowired
	private adminregisterservice adminregisterservice;

	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> admin_register(@RequestBody adminregister_model std) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			String response = adminregisterservice.registered(std);
			res.put("status", HttpStatus.OK);
			res.put("response", response);
		} catch (Exception e) {
			res.put("status", HttpStatus.BAD_REQUEST);
			res.put("message", e.getMessage());
		}
		return new ResponseEntity<Map<String, Object>>(res, (HttpStatusCode) res.get("status"));
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@Param("email") String email, @Param("password") String password) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			String response = adminregisterservice.findByLogin(email, password);
			res.put("Status", HttpStatus.OK);
			res.put("response", response);
		} catch (Exception e) {
			res.put("Status", HttpStatus.BAD_REQUEST);
			res.put("message", e.getMessage());
		}
//		}return new ResponseEntity<Map<String, Object>>(res, (HttpStatusCode) res.get("status"));
		return new ResponseEntity<Map<String, Object>>(res, (HttpStatusCode) res.get("Status"));
//		return new ResponseEntity<Map<String,Object>>(res, res.getClass("status"));
	}

	@PutMapping("/updatepassword")
	public ResponseEntity<Map<String, Object>> updatepassword(@Param("email") String email,
			@Param("password") String password) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			String response = adminregisterservice.updatepassword(email, password);
			res.put("status", HttpStatus.OK);
			res.put("response", response);

		} catch (Exception e) {
			res.put("status", HttpStatus.BAD_REQUEST);
			res.put("message", e.getMessage());
		}
		return new ResponseEntity<Map<String, Object>>(res, (HttpStatusCode) res.get("status"));
	}
}
