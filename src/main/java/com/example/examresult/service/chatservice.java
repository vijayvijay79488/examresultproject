package com.example.examresult.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.examresult.model.chatmodel;
import com.example.examresult.repository.chatrepo;

@Service
public class chatservice {
	@Autowired
	private chatrepo repo;

	public String addmsg(chatmodel std) {
		// TODO Auto-generated method stub
		repo.save(std);
		return "add succefully";
	}

	public List<chatmodel> getmsg(String user1, String user2) {
		// TODO Auto-generated method stub
//		List<chatmodel> one = repo.
		List<chatmodel> one = repo.FindChatBetweenUsers(user1, user2);
		return one;
	}

	public List<String> onlymsg(String form, String too) {
		// TODO Auto-generated method stub
		List<String> one = repo.FindMsgBetweenUsers(form, too);
		return one;
	}

}
