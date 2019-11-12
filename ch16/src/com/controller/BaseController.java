package com.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.exception.MyException;

public abstract class BaseController {
	@ExceptionHandler	//	基于@ExceptionHandler处理异常
	public String exception(HttpServletRequest request,Exception ex) {
		request.setAttribute("ex", ex);
		if (ex instanceof SQLException) {
			return "sql-error";
		} else if(ex instanceof MyException){
			return "my-error";
		} else {
			return "error";
		}
	}

}
