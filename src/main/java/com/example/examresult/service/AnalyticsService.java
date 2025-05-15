package com.example.examresult.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.examresult.dto.DepartmentAnalyticsDTO;
import com.example.examresult.dto.SemesterAnalyticsDTO;
import com.example.examresult.dto.SubjectAnalyticsDTO;
import com.example.examresult.dto.TopStudentDTO;
import com.example.examresult.model.StudentResult;
import com.example.examresult.repository.StudentResultRepository;

@Service
public class AnalyticsService {

	@Autowired
	private StudentResultRepository studentResultRepository;

	public List<SemesterAnalyticsDTO> getAllSemesterAnalytics() {
		List<StudentResult> allResults = studentResultRepository.findAll();

		// Filter out invalid semester values
		Map<Integer, List<StudentResult>> resultsBySemester = allResults.stream()
				.filter(r -> isNumeric(r.getSemester()))
				.collect(Collectors.groupingBy(r -> Integer.parseInt(r.getSemester())));

		List<SemesterAnalyticsDTO> semesterAnalyticsList = new ArrayList<>();

		for (Map.Entry<Integer, List<StudentResult>> semesterEntry : resultsBySemester.entrySet()) {
			int semester = semesterEntry.getKey();
			List<StudentResult> semesterResults = semesterEntry.getValue();

			Map<String, List<StudentResult>> resultsByDept = semesterResults.stream()
					.collect(Collectors.groupingBy(r -> r.getStudent().getDepartment()));

			List<DepartmentAnalyticsDTO> departments = new ArrayList<>();

			for (Map.Entry<String, List<StudentResult>> deptEntry : resultsByDept.entrySet()) {
				String department = deptEntry.getKey();
				List<StudentResult> deptResults = deptEntry.getValue();

				int studentCount = deptResults.size();

				List<TopStudentDTO> topStudents = deptResults.stream().map(r -> {
					int total = parseIntSafe(r.getTamil()) + parseIntSafe(r.getEnglish()) + parseIntSafe(r.getMaths())
							+ parseIntSafe(r.getScience()) + parseIntSafe(r.getSocial());
					return new TopStudentDTO(r.getStudent().getName(), r.getStudent().getRegistered(), total,
							"https://i.pravatar.cc/60?u=" + r.getStudent().getRegistered());
				}).sorted(Comparator.comparingInt(TopStudentDTO::getTotalMarks).reversed()).limit(3)
						.collect(Collectors.toList());

				List<SubjectAnalyticsDTO> subjects = new ArrayList<>();
				subjects.add(getSubjectStats(deptResults, "Tamil"));
				subjects.add(getSubjectStats(deptResults, "English"));
				subjects.add(getSubjectStats(deptResults, "Maths"));
				subjects.add(getSubjectStats(deptResults, "Science"));
				subjects.add(getSubjectStats(deptResults, "Social"));

				departments.add(new DepartmentAnalyticsDTO(department, studentCount, topStudents, subjects));
			}

			semesterAnalyticsList.add(new SemesterAnalyticsDTO(semester, departments));
		}

		return semesterAnalyticsList;
	}

	private SubjectAnalyticsDTO getSubjectStats(List<StudentResult> results, String subjectName) {
		int pass = 0, fail = 0;
		for (StudentResult result : results) {
			String markStr = getMarks(result, subjectName);
			int marks = parseIntSafe(markStr); // Use safe parsing

			if (marks >= 35) {
				pass++;
			} else {
				fail++;
			}
		}
		return new SubjectAnalyticsDTO(subjectName, pass, fail);
	}

	private String getMarks(StudentResult result, String subjectName) {
		switch (subjectName) {
		case "Tamil":
			return result.getTamil();
		case "English":
			return result.getEnglish();
		case "Maths":
			return result.getMaths();
		case "Science":
			return result.getScience();
		case "Social":
			return result.getSocial();
		default:
			return "0";
		}
	}

	private boolean isNumeric(String str) {
		return str != null && str.matches("\\d+");
	}

//	private int parseIntSafe(String str) {
//		try {
//			return Integer.parseInt(str);
//		} catch (NumberFormatException e) {
//			return 0; // or handle differently if needed
//		}
//	}
	private int parseIntSafe(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return 0; // Treat invalid marks like "A", "AB", etc. as 0
		}
	}

	public List<TopStudentDTO> getTopStudents(String semester, String department) {
		// TODO Auto-generated method stub
		List<StudentResult> results = studentResultRepository.findBySemesterAndDepartment(semester, department);

		// Group results by student registration number
		Map<String, List<StudentResult>> groupedByStudent = results.stream()
				.collect(Collectors.groupingBy(sr -> sr.getStudent().getRegistered()));

		List<TopStudentDTO> topStudents = new ArrayList<>();

		for (String reg : groupedByStudent.keySet()) {
			List<StudentResult> studentSubjects = groupedByStudent.get(reg);
			if (studentSubjects.isEmpty())
				continue;

			StudentResult firstEntry = studentSubjects.get(0);
			int totalMarks = 0;

			try {
				totalMarks += Integer.parseInt(firstEntry.getTamil());
				totalMarks += Integer.parseInt(firstEntry.getEnglish());
				totalMarks += Integer.parseInt(firstEntry.getMaths());
				totalMarks += Integer.parseInt(firstEntry.getScience());
				totalMarks += Integer.parseInt(firstEntry.getSocial());
			} catch (NumberFormatException e) {
				// Skip if marks are not valid integers
				continue;
			}

			TopStudentDTO dto = new TopStudentDTO();
			dto.setName(firstEntry.getStudent().getName());
			dto.setRegisterNumber(firstEntry.getStudent().getRegistered());
			dto.setTotalMarks(totalMarks);
			dto.setAvatarUrl("https://default-avatar.com/img.png");

			topStudents.add(dto);
		}

		// Return top 5 students sorted by total marks
		return topStudents.stream().sorted(Comparator.comparingInt(TopStudentDTO::getTotalMarks).reversed()).limit(5)
				.collect(Collectors.toList());
	}

	public List<Map<String, Object>> getStudentAnalytics(String semester, String department) {
		List<StudentResult> studentResults = studentResultRepository.findBySemesterAndDepartment(semester, department);

		if (studentResults.isEmpty()) {
			return Collections.emptyList();
		}

		Map<String, List<StudentResult>> departmentGroupedResults = studentResults.stream()
				.collect(Collectors.groupingBy(result -> result.getStudent().getDepartment()));

		List<Map<String, Object>> semesterData = new ArrayList<>();

		for (String dept : departmentGroupedResults.keySet()) {
			List<StudentResult> departmentResults = departmentGroupedResults.get(dept);
			Map<String, Object> departmentData = new HashMap<>();

			// --- Top Students
			List<Map<String, Object>> topStudents = departmentResults.stream().map(result -> {
				Map<String, Object> studentData = new HashMap<>();
				studentData.put("name", result.getStudent().getName());
				studentData.put("registerNumber", result.getRegistered());
				studentData.put("totalMarks", calculateTotalMarks(result));
				studentData.put("avatarUrl", "http://example.com/avatar.jpg"); // Placeholder
				return studentData;
			}).collect(Collectors.toList());

			// --- ✅ SUBJECTS DATA (Use the block you asked about here)
			Map<String, int[]> subjectCounts = new HashMap<>();
			String[] subjects = { "Tamil", "English", "Maths", "Science", "Social" };

			for (String subject : subjects) {
				subjectCounts.put(subject, new int[] { 0, 0 }); // [pass, fail]
			}

			for (StudentResult result : departmentResults) {
				updateSubjectCount(subjectCounts.get("Tamil"), result.getTamil());
				updateSubjectCount(subjectCounts.get("English"), result.getEnglish());
				updateSubjectCount(subjectCounts.get("Maths"), result.getMaths());
				updateSubjectCount(subjectCounts.get("Science"), result.getScience());
				updateSubjectCount(subjectCounts.get("Social"), result.getSocial());
			}

			List<Map<String, Object>> subjectsData = subjectCounts.entrySet().stream().map(entry -> {
				Map<String, Object> map = new HashMap<>();
				map.put("subject", entry.getKey());
				map.put("passCount", entry.getValue()[0]);
				map.put("failCount", entry.getValue()[1]);
				return map;
			}).collect(Collectors.toList());

			// --- Add to departmentData
			departmentData.put("department", dept);
			departmentData.put("topStudents", topStudents);
			departmentData.put("subjects", subjectsData);

			semesterData.add(departmentData);
		}

		return semesterData;
	}

	private void updateSubjectCount(int[] counts, String mark) {
		try {
			int score = Integer.parseInt(mark);
			if (score >= 40) {
				counts[0]++; // Pass
			} else {
				counts[1]++; // Fail
			}
		} catch (NumberFormatException e) {
			counts[1]++; // Treat invalid marks as fail
		}
	}

	// Helper method to calculate total marks (this should be dynamic, use actual
	// logic)
	private int calculateTotalMarks(StudentResult result) {
		int totalMarks = 0;
		totalMarks += parseMarks(result.getTamil());
		totalMarks += parseMarks(result.getEnglish());
		totalMarks += parseMarks(result.getMaths());
		totalMarks += parseMarks(result.getScience());
		totalMarks += parseMarks(result.getSocial());
		return totalMarks;
	}

	// Helper method to parse individual subject marks
	private int parseMarks(String marks) {
		try {
			return Integer.parseInt(marks);
		} catch (NumberFormatException e) {
			return 0; // If marks are not valid integers, return 0
		}
	}

}