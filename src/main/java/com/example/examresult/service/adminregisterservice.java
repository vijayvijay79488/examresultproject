package com.example.examresult.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.examresult.model.adminregister_model;
//import com.example.examresult.model.adminregister_model;
import com.example.examresult.repository.adminregisterrepo;

class maunal extends Exception {
	public maunal(String msg) {
		super(msg);
	}
}

@Service
public class adminregisterservice {
	@Autowired
	private adminregisterrepo adminregisterrepo;

	public String registered(adminregister_model std) throws manual {
		// TODO Auto-generated method stub
		adminregister_model existingadminregistermail = adminregisterrepo.findByEmail(std.getEmail());

		if (existingadminregistermail == null) {
			adminregisterrepo.save(std);
			return "update ";
		}
		throw new manual("already mail existed");

	}

	public String findByLogin(String email, String password) throws maunal {
		// TODO Auto-generated method stub
		adminregister_model model = adminregisterrepo.findByEmail(email);
		if (model == null) {
			return "no id found";
		} else {
			if (password.equals(model.getPassword())) {
				return "login succful";
			} else {
				throw new maunal("Retry");
			}
		}

	}

	public String updatepassword(String email, String password) throws maunal {
		adminregister_model checking = adminregisterrepo.findByEmail(email);
		if (checking != null) {
			checking.setPassword(password);
			checking.setDepartment(checking.getDepartment());
			checking.setEmail(email);
			checking.setName(checking.getName());
			adminregisterrepo.save(checking);
			return "updated succedslu";
		}
		throw new maunal("no mail id found");
	}

}
