package com.example.examresult.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.examresult.dto.SemesterAnalyticsDTO;
import com.example.examresult.model.studentinfo_model;
import com.example.examresult.model.studentregister;
import com.example.examresult.model.studentresult_model;
import com.example.examresult.service.AnalyticsService;
import com.example.examresult.service.ResultService;
import com.example.examresult.service.studentinfoservice;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/student")
public class studentinfocontroller {
	@Autowired
	private studentinfoservice studentinfoservice;
	@Autowired
	private ResultService resultService;

	@PostMapping("/addstd")
	public ResponseEntity<Map<String, Object>> addstd(@RequestBody studentregister model) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			String response = studentinfoservice.addstd(model);
			res.put("status", HttpStatus.OK);
			res.put("response", response);
		} catch (Exception e) {
			res.put("status", HttpStatus.BAD_REQUEST);
			res.put("message", e.getMessage());

		}
		return new ResponseEntity<Map<String, Object>>(res, (HttpStatusCode) res.get("status"));
	}

	@PostMapping("/postresult")
	public ResponseEntity<Map<String, Object>> postresult(@RequestBody studentresult_model model) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			String response = studentinfoservice.postresult(model);
			res.put("status", HttpStatus.OK);
			res.put("response", response);
		} catch (Exception e) {
			res.put("status", HttpStatus.BAD_REQUEST);
			res.put("message", e.getMessage());

		}
		return new ResponseEntity<Map<String, Object>>(res, (HttpStatusCode) res.get("status"));
	}

	@PostMapping("/update")
	public ResponseEntity<Map<String, Object>> updateresult(@RequestBody studentresult_model model) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			String response = studentinfoservice.update(model);
			res.put("status", HttpStatus.OK);
			res.put("response", response);
		} catch (Exception e) {
			res.put("status", HttpStatus.BAD_REQUEST);
			res.put("message", e.getMessage());

		}
		return new ResponseEntity<Map<String, Object>>(res, (HttpStatusCode) res.get("status"));
	}

	@GetMapping("/viewresult")
	public ResponseEntity<Map<String, Object>> viewresult(@RequestParam("registered") String registered,
			@RequestParam("dob") String dob, @RequestParam("sem") String semester) {
		Map<String, Object> res = new HashMap<>();
		try {
			Map<String, Object> response = resultService.getStudentResult(registered, dob, semester);

//			System.out.println("Response size: " + response.size()); // Add this for debugging

			res.put("status", HttpStatus.OK);
			res.put("response", response);
		} catch (Exception e) {
			res.put("status", HttpStatus.BAD_REQUEST);
			res.put("message", e.getMessage());
		}
		return new ResponseEntity<>(res, (HttpStatus) res.get("status"));
	}

	@GetMapping("/viewstudentinfo")
	public ResponseEntity<Map<String, Object>> viewstudentinfo(@RequestParam("registered") String registered,
			@RequestParam("dob") String dob) {
		Map<String, Object> res = new HashMap<>();
		try {
			studentregister response = studentinfoservice.getstudentinfo(registered, dob);

//			System.out.println("Response size: " + response.size()); // Add this for debugging

			res.put("status", HttpStatus.OK);
			res.put("response", response);
		} catch (Exception e) {
			res.put("status", HttpStatus.BAD_REQUEST);
			res.put("message", e.getMessage());
		}
		return new ResponseEntity<>(res, (HttpStatus) res.get("status"));
	}

	@GetMapping("/addrevalution")
	public ResponseEntity<Map<String, Object>> addrevalution(@RequestParam("registered") String registered) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			List<studentinfo_model> response = studentinfoservice.addrevalution(registered);
			res.put("status", HttpStatus.OK);
			res.put("response", response);

		} catch (Exception e) {
			res.put("status", HttpStatus.BAD_REQUEST);
			res.put("message", e.getMessage());

		}
		return new ResponseEntity<Map<String, Object>>(res, (HttpStatusCode) res.get("status"));
	}

	@Autowired
	private AnalyticsService analyticsService;

	@GetMapping("/all-semesters")
	public List<SemesterAnalyticsDTO> getAllAnalytics() {
		return analyticsService.getAllSemesterAnalytics();
	}

}
