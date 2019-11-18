package com.service.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.dao.admin.AdminLoginDao;
import com.po.Auser;
@Service("adminLoginService")
@Transactional
public class AdminLoginServiceImpl implements AdminLoginService {
	@Autowired
	private AdminLoginDao adminLoginDao;
	@Override
	public String login(Auser auser, Model model, HttpSession session) {
		// TODO Auto-generated method stub
		if (adminLoginDao.login(auser)!=null && adminLoginDao.login(auser).size()>0) {
			session.setAttribute("auser", auser);
			return "admin/main";
		}
		model.addAttribute("msg", "用户名或密码错误！");
		return "admin/login";
	}

}
