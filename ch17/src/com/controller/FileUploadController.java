package com.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.po.FileDomain;
import com.po.MoreFileDomain;

@Controller
public class FileUploadController {
	private static final Log logger=LogFactory.getLog(FileUploadController.class);
	@RequestMapping("/onefile")
	public String oneFileUpload(@ModelAttribute FileDomain fileDomain, HttpServletRequest request) {
		String realpath=request.getServletContext().getRealPath("uploadfiles");
		String fileName=fileDomain.getMyfile().getOriginalFilename();
		File targetFile=new File(realpath,fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			fileDomain.getMyfile().transferTo(targetFile);
			logger.info("成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showOne";
	}
	@RequestMapping("/moreFile")
	public String moreFileupload(@ModelAttribute MoreFileDomain moreFileDomain,HttpServletRequest request) {
		String realpath=request.getServletContext().getRealPath("uploadfiles");
		File targetDir=new File(realpath);
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}
		List<MultipartFile> files=moreFileDomain.getMyfile();
		for (int i = 0; i < files.size(); i++) {
			MultipartFile file=files.get(i);
			String fileName=file.getOriginalFilename();
			File targetFile=new File(realpath,fileName);
			
			try {
				file.transferTo(targetFile);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		logger.info("成功");
		return "showMore";
	}

}
