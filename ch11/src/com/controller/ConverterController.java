package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.po.Goods;

@Controller
@RequestMapping("/my")
public class ConverterController {
	@RequestMapping("/converter")
	public String myConverter(@RequestParam("goods") Goods goods,Model model) {
		model.addAttribute("goods", goods);
		return "showGoods";
	}
}
