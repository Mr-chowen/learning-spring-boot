package com.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.po.User;
import com.servcie.UserServcie;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServcie userServcie;
	@RequestMapping("/input")
	public String inputUser(Model model) {
		HashMap<String, String> hobbys=new HashMap<String, String>();
		hobbys.put("篮球", "篮球");
		hobbys.put("足球", "足球");
		hobbys.put("网球", "网球");
		hobbys.put("羽毛球", "羽毛球");
		model.addAttribute("user", new User());
		model.addAttribute("hobbys", hobbys);
		model.addAttribute("carrers", new String[] {"教师","程序员","IT民工","其他"});
		model.addAttribute("houseRegisters", new String[] {"深圳","北京","上海","其他"});
		return "userAdd";
	} 
	@RequestMapping("/save")
	public String addUser(@ModelAttribute User user,Model model) {
		
		if (userServcie.addUser(user)) {
			return "redirect:/user/list";
		} else {
			HashMap<String, String> hobbys=new HashMap<String, String>();
			hobbys.put("篮球", "篮球");
			hobbys.put("足球", "足球");
			hobbys.put("网球", "网球");
			hobbys.put("羽毛球", "羽毛球");
			model.addAttribute("hobbys", hobbys);
			model.addAttribute("carrers", new String[] {"教师","程序员","IT民工","其他"});
			model.addAttribute("houseRegisters", new String[] {"深圳","北京","上海","其他"});
			return "userAdd";
		}
	}
	
	@RequestMapping("/list")
	public String listUsers(Model model) {
		List<User> users=userServcie.getUsers();
		model.addAttribute("users", users);
		return "userList";
	}
}
