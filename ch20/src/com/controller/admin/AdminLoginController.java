package com.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.po.Auser;
import com.service.admin.AdminLoginService;

@Controller
public class AdminLoginController {
	@Autowired
	private AdminLoginService adminLoginService;
	@RequestMapping("/admin")
	public String toLogin(@ModelAttribute Auser auser) {
		return "admin/login";
	}
	@RequestMapping("/admin/login")
	public String login(@ModelAttribute Auser auser,Model model,HttpSession session) {
		return adminLoginService.login(auser, model, session);
	}
	@RequestMapping("/exit")
	public String exit(@ModelAttribute Auser auser,HttpSession session) {
		session.invalidate();
		return "admin/login";
	}

}
