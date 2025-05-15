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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.examresult.dto.StudentResponseDTO;
import com.example.examresult.model.revalutionmodel;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/revaluation")

public class RevaluationController {

	@Autowired
	private com.example.examresult.service.revalutionservice revalutionservice;

//	@PutMapping("/update-status/{id}")
//	public ResponseEntity<String> updateStatus(@PathVariable int id, @RequestParam String status) {
//		revalutionmodel request = repo.findById(id).orElse(null);
//		if (request != null) {
//			request.setStatus(status);
//			repo.save(request);
//			return ResponseEntity.ok("Status updated to " + status);
//		} else {
//			return ResponseEntity.status(404).body("Request not found");
//		}
//	}

//	@GetMapping("/pending")
//	public List<revalutionmodel> getPendingRevaluation() {
//		return repo.findByStatus("Pending");
//	}

	@GetMapping("/approvegetpost")
	public ResponseEntity<Map<String, Object>> approvepost(@Param("registered") String registered,
			@Param("semester") String semester) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			StudentResponseDTO response = revalutionservice.approvepost(registered, semester);
			res.put("status", HttpStatus.OK);
			res.put("response", response);
		} catch (Exception e) {
			res.put("status", HttpStatus.BAD_REQUEST);
			res.put("message", e.getMessage());
		}
		return new ResponseEntity<Map<String, Object>>(res, (HttpStatusCode) res.get("status"));
	}

	@PostMapping("/apply")
	public ResponseEntity<Map<String, Object>> revalutionadd(@RequestBody revalutionmodel std) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			String response = revalutionservice.addrevalution(std);
			res.put("status", HttpStatus.OK);
			res.put("response", response);
		} catch (Exception e) {
			res.put("status", HttpStatus.BAD_REQUEST);
			res.put("message", e.getMessage());
		}
		return new ResponseEntity<Map<String, Object>>(res, (HttpStatusCode) res.get("status"));
	}

	@GetMapping("/revaluations")
	public ResponseEntity<Map<String, Object>> getallrevalution() {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			List<revalutionmodel> response = revalutionservice.getallrevaludtion();
			res.put("status", HttpStatus.OK);
			res.put("response", response);
		} catch (Exception e) {
			res.put("status", HttpStatus.BAD_REQUEST);
			res.put("message", e.getMessage());
		}
		HttpStatus status = (HttpStatus) res.get("status");
		return new ResponseEntity<>(res, status);

	}

	@PostMapping("/reject")
	public ResponseEntity<Map<String, Object>> removedatainrevalition(@Param("registered") String registered,
			@Param("semester") String semester) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			String response = revalutionservice.removedatainrevalition(registered, semester);
			System.out.println(response);
			res.put("status", HttpStatus.OK);
			res.put("response", response);
		} catch (Exception e) {
			res.put("status", HttpStatus.BAD_REQUEST);
			res.put("message", e.getMessage());
		}
		return new ResponseEntity<Map<String, Object>>(res, (HttpStatusCode) res.get("status"));

	}

//	@PostMapping("/approve")
//	public ResponseEntity<String> approveRevaluation(@RequestBody revalutionmodel request) {
//		revalutionmodel existing = repo.findById(request.getId()).orElse(null);
//		if (existing != null) {
//			existing.setStatus("Approved");
//			repo.save(existing);
//			return ResponseEntity.ok("Request approved");
//		} else {
//			return ResponseEntity.status(404).body("Request not found");
//		}
//	}

//	@PostMapping("/reject")
//	public ResponseEntity<String> rejectRevaluation(@RequestBody revalutionmodel request) {
//		revalutionmodel existing = repo.findById(request.getId()).orElse(null);
//		if (existing != null) {
//			existing.setStatus("Rejected");
//			repo.save(existing);
//			return ResponseEntity.ok("Request rejected");
//		} else {
//			return ResponseEntity.status(404).body("Request not found");
//		}
//	}

}