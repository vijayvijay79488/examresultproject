package com.example.examresult.service;

import java.util.ArrayList;
import java.util.Comparator;
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
}
