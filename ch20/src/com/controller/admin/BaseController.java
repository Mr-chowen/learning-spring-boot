package com.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class BaseController {
	@ModelAttribute
	public void isLogin(HttpSession session,HttpServletRequest request) {
		if (session.getAttribute("auser")==null) {
			
		}
	}

}
