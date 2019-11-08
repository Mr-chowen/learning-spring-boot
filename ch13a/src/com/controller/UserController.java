package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.po.User;

@Controller
public class UserController {
	/*
	 * 	登录页面初始化
	 */
	@RequestMapping("/toLogin")
	public String initLogin() {
		return "login";
	} 
	/*
	 * 	处理登录
	 */
	@RequestMapping("/login")
	public String login(User user,Model model,HttpSession session) {
		if ("chengcheng".equals(user.getUname()) && "123456".equals(user.getPassword())) {
			session.setAttribute("user", user);
			return "redirect:main";
		} 
		model.addAttribute("msg", "用户名或密码错误！");
		return "login";
	}
	/*
	 * 	跳转至主页面
	 */
	@RequestMapping("/main")
	public String toMain() {
		return "main";
	}
	/*
	 * 	退出登录
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
}
