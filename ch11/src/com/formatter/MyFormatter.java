package com.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;
/*
 * Formatter只能对字符串进行转换
 */
public class MyFormatter implements Formatter<Date> {

	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public String print(Date arg0, Locale arg1) {
		// TODO Auto-generated method stub
		return sdf.format(arg0);
	}

	@Override
	public Date parse(String arg0, Locale arg1) throws ParseException {
		// TODO Auto-generated method stub
		return sdf.parse(arg0);
	}

}
