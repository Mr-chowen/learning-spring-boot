package com.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.dao.admin.AdminUserDao;
@Service("adminUserService")
@Transactional
public class AdminUserServiceImpl implements AdminUserService {
	@Autowired
	private AdminUserDao adminUserDao;

	@Override
	public String userInfo(Model model) {
		// TODO Auto-generated method stub
		model.addAttribute("userList", adminUserDao.userInfo());
		return "admin/userManager";
	}

	@Override
	public String deleteuserManager(Integer id, Model model) {
		// TODO Auto-generated method stub
		if (adminUserDao.deleteuserManager(id)>0) 
			model.addAttribute("msg", "成功删除用户！");
		return "forward:/adminUser/userInfo";
	}

}
