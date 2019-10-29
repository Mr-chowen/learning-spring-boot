package com.annotation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.annotation.service.TestService;

@Controller
public class TestController {
	@Autowired
	private TestService testService;
	
	public void save() {
		testService.save();
		System.out.println("controller say good study !");
	}
}
