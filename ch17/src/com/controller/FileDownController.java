package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileDownController {
	private static final Log logger=LogFactory.getLog(FileDownController.class);
	/*
	 * 显示要下载的文件
	 */
	@RequestMapping("showDownFiles")
	public String show(HttpServletRequest request,Model model) {
		String realpath=request.getServletContext().getRealPath("uploadfiles");
		File dir=new File(realpath);
		File files[]=dir.listFiles();
		ArrayList<String> fileName=new ArrayList<String>();
		for (int i = 0; i < files.length; i++) {
			fileName.add(files[i].getName());
		}
		model.addAttribute("files", fileName);
		return "showDownFiles";
	}
	@RequestMapping("down")
	public String down(@RequestParam String filename,HttpServletRequest request,HttpServletResponse response) {
		String aFilePath=null;
		FileInputStream in=null;
		ServletOutputStream out=null;
		try {
			aFilePath=request.getServletContext().getRealPath("uploadfiles");
			response.setHeader("Content-Type", "application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment;filename="+toUTF8String(filename));
			in=new FileInputStream(aFilePath+"\\"+filename);
			out=response.getOutputStream();
			out.flush();
			int aRead=0;
			byte b[]=new byte[1024];
			while ((aRead=in.read(b))!=-1 & in!=null) {
				out.write(b, 0, aRead);
			}
			out.flush();
			in.close();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		logger.info("下载成功");
		return null;
	}
	/*
	 * 下载文件时中文文件名的字符编码转换方法
	 */
	private String toUTF8String(String str) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer();
		int len=str.length();
		for (int i = 0; i < len; i++) {
			char c=str.charAt(i);
			if (c>=0 & c<255) {
				sb.append(c);
			} else {
				byte b[];
				try {
					b=Character.toString(c).getBytes("UTF-8");
				} catch (Exception e) {
					e.printStackTrace();
					b=null;
				}
				for (int j = 0; j < b.length; j++) {
					int k=b[j];
					if (k<0) {
						k&=255;
					}
					sb.append("%"+Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

}
