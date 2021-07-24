package com.care.root.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.root.service.FileService;

@Controller
public class FileDownloadController {
	@GetMapping("download")
	public void download(@RequestParam("file") String fileName, HttpServletResponse response) throws Exception {
		
		response.addHeader("Content-disposition", "attachment; fileName="+fileName);
		File file = new File(FileService.IMAGE_REPO+"\\"+fileName);
		FileInputStream in = new FileInputStream(file);
		FileCopyUtils.copy(in, response.getOutputStream());
		in.close();
	}
}
