package com.example.examresult.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.examresult.model.studentinfo;
import com.example.examresult.service.studentinfoservice;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/student")
public class studentinfocontroller {
	@Autowired
	private studentinfoservice studentinfoservice;

//	@PostMapping("/addstd")
	public ResponseEntity<Map<String, Object>> addstudent(@RequestParam Long registered, @RequestParam Integer semester,
			@RequestParam String dob, @RequestBody studentinfo stdinfo) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			String response = studentinfoservice.addstd(registered, semester, dob, stdinfo);
			res.put("status", HttpStatus.OK);
			res.put("response", response);
		} catch (Exception e) {
			res.put("status", HttpStatus.BAD_REQUEST);
			res.put("message", e.getMessage());
		}
		return new ResponseEntity<Map<String, Object>>(res, (HttpStatusCode) res.get("status"));
	}

//	@PostMapping("/addstd1")
//	public ResponseEntity<Map<String, Object>> addstudent(@RequestBody addingstudentdetails_model add) {
//		Map<String, Object> res = new HashMap<String, Object>();
//		try {
//			String response = studentinfoservice.addstudent(add);
//			res.put("status", HttpStatus.OK);
//			res.put("response", response);
//		} catch (Exception e) {
//			res.put("status", HttpStatus.BAD_REQUEST);
//			res.put("message", e.getMessage());
//		}
//		return new ResponseEntity<Map<String, Object>>(res, (HttpStatusCode) res.get("status"));
//	}

//	@GetMapping("/fetchstd")
//	public ResponseEntity<Map<String, Object>> fetchstd(@RequestParam("id") long id, @RequestParam("dob") String dob) {
//		Map<String, Object> res = new HashMap<String, Object>();
//		try {
////			studentinfo response = studentinfoservice.getbyid(id, dob,semester);
//			List<studentinfo> response = studentinfoservice.getbyid(id, dob);
//			res.put("status", HttpStatus.OK);
//			res.put("response", response);
//		} catch (Exception e) {
//			res.put("status", HttpStatus.BAD_REQUEST);
//			res.put("message", e.getMessage());
//		}
//		return new ResponseEntity<Map<String, Object>>(res, (HttpStatusCode) res.get("status"));
//
//	}

	@GetMapping("/{registered}/{semester}/{dob}")
	public ResponseEntity<Map<String, Object>> getStudent(@PathVariable Long registered, @PathVariable Integer semester,
			@PathVariable String dob) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
//			Optional Object response = studentinfoservice.getStudentInfo(registered, semester, dob);
			Object response = studentinfoservice.getStudentInfo(registered, semester, dob);
			res.put("status", HttpStatus.OK);
			res.put("response", response);
		} catch (Exception e) {
			res.put("status", HttpStatus.BAD_REQUEST);
			res.put("messgae", e.getMessage());
		}
		return new ResponseEntity<Map<String, Object>>(res, (HttpStatusCode) res.get("status"));
	}

	@GetMapping("/getresult")
	public ResponseEntity<Map<String, Object>> getresult(@Param("registered") Long registered,
			@Param("dob") String dob) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			Object reponse = studentinfoservice.getresult(registered, dob);
			res.put("status", HttpStatus.OK);
			res.put("response", reponse);
		} catch (Exception e) {
			res.put("status", HttpStatus.BAD_REQUEST);
			res.put("message", e.getMessage());
		}
		return new ResponseEntity<Map<String, Object>>(res, (HttpStatusCode) res.get("status"));
	}

}
