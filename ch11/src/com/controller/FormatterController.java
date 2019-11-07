package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.po.GoodsModel;

@Controller
@RequestMapping("/my")
public class FormatterController {
	
	@RequestMapping("/formatter")
	public String myConverter(GoodsModel gm,Model model) {
		model.addAttribute("goods", gm);
		return "showGoodsA";
	}
}
