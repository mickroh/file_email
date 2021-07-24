package com.care.root.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.root.service.MailService;

@Controller
public class MailController {
	@Autowired MailService ms;
	@GetMapping("sendmail")
	public void sendSimpleMail(HttpServletRequest request,
						HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String str = new String();
		str += "<html><body>";
		str+= "<h1>제품소개</h1>";
		str += "<a href='https://tv.naver.com/l/81426'>";
		str += "<img src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTbjX1ArTUFh7eckoJKm2lYMlpsrHCt3hTVmw&usqp=CAU'>";
		str += "</a></body></html>";
		
	ms.sendMail("mickroh0214@gmail.com","테스트 메일(제목)",
								str);
		
		out.print("메일 전송 완료");
	}
	@GetMapping("auth_form")
	public String authForm() {
		return "auth_form";
	}
	@GetMapping("auth")
	public String auth(HttpServletRequest request) {
		ms.auth(request);
		return "redirect:http://www.gmail.com";
	}
	@RequestMapping("auth_check")
	public String auth_check(@RequestParam("userid") String userid,
							@RequestParam("userkey") String userkey,
							HttpSession session	) {
		String sessionKey = (String)session.getAttribute(userid);
		if(sessionKey != null) {
			if(sessionKey.equals(userkey)) {
				session.setAttribute("userid", userid);
			}
		}
		return "redirect:auth_form";
	}
}










