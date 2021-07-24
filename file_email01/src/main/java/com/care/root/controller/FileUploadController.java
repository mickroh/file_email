package com.care.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import com.care.root.service.FileService;

@Controller
public class FileUploadController {
	@Autowired FileService fs;
	@RequestMapping(value="/form")
	public String form() {
		return "uploadForm";
	}
	@PostMapping("upload")
	public String upload(MultipartHttpServletRequest mul) {
		System.out.println("test");
		fs.fileProcess(mul);
		return "redirect:form";
	}
	@GetMapping("views")
	public String views(Model model) {
		fs.getShoesImage(model);
		return "result";
	}
}
