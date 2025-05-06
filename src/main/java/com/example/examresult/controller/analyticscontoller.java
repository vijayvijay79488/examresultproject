package com.example.examresult.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.examresult.dto.TopStudentDTO;
import com.example.examresult.service.AnalyticsService;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "*")
public class analyticscontoller {
	@Autowired
	private AnalyticsService AnalyticsService;

	@GetMapping("/top-students")
	public List<TopStudentDTO> getTopStudentsByDepartmentAndSemester(@RequestParam String semester,
			@RequestParam String department) {
		return AnalyticsService.getTopStudents(semester, department);
	}

	@GetMapping("/api/analytics/top-students")
	public List<Map<String, Object>> getStudentAnalytics(@RequestParam("semester") String semester,
			@RequestParam("department") String department) {
		return AnalyticsService.getStudentAnalytics(semester, department);
	}
}
