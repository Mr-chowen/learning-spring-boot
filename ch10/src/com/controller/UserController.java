package com.controller;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.po.User;
import com.service.UserService;
@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Log logger = LogFactory.getLog(UserController.class);
	
	@Autowired
	public UserService userService;
	/*
	 * 处理登录
	 */
	@RequestMapping("/login")
	public String login(User user, HttpSession session, Model model) {
		if(userService.login(user)){
			session.setAttribute("u", user);
			logger.info("成功");
			return "main";
		}else{
			logger.info("失败");
			model.addAttribute("messageError", "用户名或密码错误！");
			return "login";
		}	
	}
	/**
	 *	处理注册
	 */
	@RequestMapping("/register")
	public String register(@ModelAttribute("user") User user) {
		if(userService.register(user)){
			logger.info("成功");
			return "login";
		}else{
			logger.info("失败");
			return "register";
		}
	}
}
