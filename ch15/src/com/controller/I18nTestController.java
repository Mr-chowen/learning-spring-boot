package com.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class I18nTestController {
	@RequestMapping("/i18nTest")
	public String first(Locale locale) {
		return "first";
	}
	
}
