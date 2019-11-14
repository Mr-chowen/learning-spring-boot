package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.UserDao;
import com.po.User;

@Controller
public class UserController {
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/select")
	public String select(User user,Model model) {
		List<User> list=userDao.selectUserByUname(user);
		model.addAttribute("userList", list);
		return "userList";
	}
}
