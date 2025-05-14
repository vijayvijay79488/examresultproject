package com.example.examresult.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.examresult.model.chatmodel;
import com.example.examresult.service.chatservice;

@RestController
@RequestMapping("/chat")
public class chatcontroller {
	@Autowired
	private chatservice service;

	@PostMapping("/addmsg")
	public String addmsg(@RequestBody chatmodel std) {
		return service.addmsg(std);
	}

	@GetMapping("/getmsg")
	public List<chatmodel> getmsg(@Param("form") String form, @Param("too") String too) {
		return service.getmsg(form, too);
	}

	@GetMapping("/onlymsg")
	public List<String> onlymsg(@Param("form") String form, @Param("too") String too) {
		return service.onlymsg(form, too);
	}
}
