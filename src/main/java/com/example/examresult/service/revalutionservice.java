package com.example.examresult.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.examresult.dto.StudentResponseDTO;
import com.example.examresult.dto.SubjectDTO;
import com.example.examresult.model.StudentInfo;
import com.example.examresult.model.StudentInfoId;
import com.example.examresult.model.revalutionmodel;
import com.example.examresult.model.studentrevalutionRecords;
import com.example.examresult.repository.StudentInfoRepository;
import com.example.examresult.repository.revalutionrepo;
import com.example.examresult.repository.studentrevalutionrecordsrepo;

class manual extends Exception {
	manual(String msg) {
		super(msg);
	}
}

@Service
public class revalutionservice {
	@Autowired
	private revalutionrepo repo;
	@Autowired
	private com.example.examresult.repository.studentinforepo studentinforepo;
	@Autowired
	private studentrevalutionrecordsrepo repos;
	@Autowired
	private StudentInfoRepository repository;

	public String addrevalution(revalutionmodel std) throws maunal {
//		revalutionmodel one = repo.findByRegisteredAndSemester(std.getRegistered(), std.getSemester());
		revalutionmodel one = repo.findByRegisteredAndSemester(std.getRegistered(), std.getSemester());
		studentrevalutionRecords rev = new studentrevalutionRecords();
//		rev.setRegistered(std.getRegistered());
//		rev.setSemester(std.getSemester());
//		rev.setSubject(std.getSubject());
//		repos.save(rev);
		if (one != null) {
			throw new maunal("your already applied ");
		}
		// TODO Auto-generated method stub
//		revalutionmodel onnn = new revalutionmodel();
//		onnn.setDate(std.getDate());
//		onnn.setRegistered(std.getRegistered());
//		onnn.setSemester(std.getSemester());
//		onnn.setStatus(std.getStatus());
//		onnn.setStatus("pending");
		rev.setRegistered(std.getRegistered());
		rev.setSemester(std.getSemester());
		rev.setSubject(std.getSubject());
		rev.setStatus("pending");
		repos.save(rev);
		repo.save(std);
		return "update succstly";
	}

	public List<revalutionmodel> getallrevaludtion() {
		return repo.findAll();
		// TODO Auto-generated method stub
//		return null;
	}

//	public studentinfo_model approvepost(String registered, String semester) {
//		// TODO Auto-generated method stub
//		studentinfo_model one = studentinforepo.findByRegisteredAndSemester(registered, semester);
//		revalutionmodel to = repo.findByRegisteredAndSemester(registered, semester);
////		revalutionmodel jlkj = new revalutionmodel();
////		jlkj = to;
//		if (to != null) {
//			to.setStatus("approve");
//			repo.save(to);
//		}
//
//		return one;
//
//	}
	public StudentResponseDTO approvepost(String registered, String semester) {
		StudentInfo student = repository.findById(new StudentInfoId(registered, semester))
				.orElseThrow(() -> new RuntimeException("Student not found"));

		// Fetch revaluation record for the student
		Optional<studentrevalutionRecords> revalRecordOpt = repos.findFirstByRegisteredAndSemester(registered,
				semester);

		// Get subjects requested for revaluation
		List<String> revalSubjects = revalRecordOpt.map(studentrevalutionRecords::getSubject).orElse(List.of());

		List<SubjectDTO> subjects = new ArrayList<>();
		subjects.add(new SubjectDTO("Tamil", Integer.parseInt(student.getTamil()), revalSubjects.contains("Tamil")));
		subjects.add(
				new SubjectDTO("English", Integer.parseInt(student.getEnglish()), revalSubjects.contains("English")));
		subjects.add(new SubjectDTO("Mathematics", Integer.parseInt(student.getMaths()),
				revalSubjects.contains("Mathematics")));
		subjects.add(
				new SubjectDTO("Science", Integer.parseInt(student.getScience()), revalSubjects.contains("Science")));
		subjects.add(new SubjectDTO("Social Science", Integer.parseInt(student.getSocial()),
				revalSubjects.contains("Social Science")));

		return new StudentResponseDTO(student.getId().getRegistered(), student.getId().getSemester(), subjects);
	}

	public String removedatainrevalition(String registered, String semester) throws maunal {
		// TODO Auto-generated method stub
		revalutionmodel to = repo.findByRegisteredAndSemester(registered, semester);
		if (to == null) {
			throw new maunal("no data found");
		}
		List<studentrevalutionRecords> one = repos.findByRegisteredAndSemester(registered, semester);
		one.get(0).setStatus("reject");
		repos.save(one.get(0));
		repo.delete(to);
		return "successuly done";
	}

	public String removeafterpostresult(String registered, String semester) throws maunal {
		// TODO Auto-generated method stub
		revalutionmodel to = repo.findByRegisteredAndSemester(registered, semester);
		if (to == null) {
			throw new maunal("no data found");
		}

		repo.delete(to);
		return "successuly done";
	}

}
