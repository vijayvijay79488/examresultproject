package com.example.examresult.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.examresult.model.revalutionmodel;
import com.example.examresult.model.studentinfo_model;
import com.example.examresult.repository.revalutionrepo;

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

	public String addrevalution(revalutionmodel std) throws maunal {
//		revalutionmodel one = repo.findByRegisteredAndSemester(std.getRegistered(), std.getSemester());
		revalutionmodel one = repo.findByRegisteredAndSemester(std.getRegistered(), std.getSemester());
		if (one != null) {
			throw new maunal("your already applied ");
		}
		// TODO Auto-generated method stub
		revalutionmodel onnn = new revalutionmodel();
		onnn.setDate(std.getDate());
		onnn.setRegistered(std.getRegistered());
		onnn.setSemester(std.getSemester());
		onnn.setStatus(std.getStatus());
		onnn.setStatus("pending");
		repo.save(std);
		return "update succstly";
	}

	public List<revalutionmodel> getallrevaludtion() {
		return repo.findAll();
		// TODO Auto-generated method stub
//		return null;
	}

	public studentinfo_model approvepost(String registered, String semester) {
		// TODO Auto-generated method stub
		studentinfo_model one = studentinforepo.findByRegisteredAndSemester(registered, semester);
		revalutionmodel to = repo.findByRegisteredAndSemester(registered, semester);
//		revalutionmodel jlkj = new revalutionmodel();
//		jlkj = to;
		if (to != null) {
			to.setStatus("approve");
			repo.save(to);
		}

		return one;

	}

	public String removedatainrevalition(String registered, String semester) throws maunal {
		// TODO Auto-generated method stub
		revalutionmodel to = repo.findByRegisteredAndSemester(registered, semester);
		if (to == null) {
			throw new maunal("no data found");
		}
		repo.delete(to);

		return "successuly done";
	}

}
