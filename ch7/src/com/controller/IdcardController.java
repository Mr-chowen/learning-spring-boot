package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dao.IdcardDao;
import com.pojo.Idcard;
@Controller
public class IdcardController {
	@Autowired
	private IdcardDao idcardDao;
	
	public void test() {
		Idcard ic=new Idcard();
		ic.setCode("129214827");
		int add=idcardDao.addIdcard(ic);
		System.out.println("添加了"+add+"条记录");
		
		System.out.println(idcardDao.selectCodeById(1).toString());
		
	}
}
