package com.care.root.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.dto.ShoesDTO;
import com.care.root.mybatis.FileMapper;

@Service
public class FileServiceImpl implements FileService{
	@Autowired FileMapper fm;
	public void fileProcess(MultipartHttpServletRequest mr) {
		ShoesDTO dto = new ShoesDTO();
		dto.setId(mr.getParameter("id"));
		dto.setName(mr.getParameter("name"));
		System.out.println("id : " +mr.getParameter("id"));
		System.out.println("name : " +mr.getParameter("name"));
		
		MultipartFile file = mr.getFile("file");
		System.out.println("file: "+file.getOriginalFilename());
		if(file.getSize() !=0) {
			SimpleDateFormat simple =
					new SimpleDateFormat("yyyyMMddHHmmss-");
			Calendar calendar = Calendar.getInstance();
			String saveFileName =
		simple.format(calendar.getTime()) + file.getOriginalFilename();
			File saveFile = new File(IMAGE_REPO + "\\" + saveFileName);
			dto.setImgName(saveFileName);
			try {
				file.transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			dto.setImgName("nan");
		}
		fm.saveData(dto);
	}
	public void getShoesImage(Model model) {
		model.addAttribute("list", fm.getShoesImage() );
	}
}
