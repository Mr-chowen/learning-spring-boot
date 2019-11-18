package com.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.admin.AdminUserService;

@Controller
@RequestMapping("/adminUser")
public class AdminUserController extends BaseController {
	@Autowired
	private AdminUserService adminUserServcie;
	@RequestMapping("/userInfo")
	public String userInfo(Model model) {
		return adminUserServcie.userInfo(model);
	}
	@RequestMapping("/deleteuserManager")
	public String deleteuserManager(Integer id,Model model) {
		return adminUserServcie.deleteuserManager(id, model);
	}
}
