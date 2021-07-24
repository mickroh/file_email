package com.care.root.service;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired JavaMailSender mailSender;
	public void sendMail(String to, String subject, String body) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = 
						new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setSubject(subject);//제목
			messageHelper.setTo(to);//보내는곳
			messageHelper.setText(body , true);//내용
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void auth(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userid = "mickroh0214";
		String userkey = rand();
		session.setAttribute(userid, userkey);
		String body="";
		body += "<h2>안녕하세요</h2>";
		body += "<p>인증하기 버튼을 누르면 로그인이 됩니다</p>";
		body += "<a href='http://localhost:8085"
		 		+ request.getContextPath()+"/auth_check?userid="
				+ userid + "&userkey="+userkey+"'>인증하기</a>";
		sendMail("mickroh0214@gmail.com","이메일 인증입니다",body);
	}
	
	private String rand() {
		Random ran = new Random();
		String str = "";
		int num;
		while(str.length() != 20) {		// 48 ~ 122
			num = ran.nextInt(75) + 48; //0~74 + 48(숫자, 소문자, 대문자)
			if( (num>=48 && num <=57) || (num>=65 && num <=90) 
										|| (num>=97 && num <=122) ) {
				str += (char)num;
			}
		}
		return str;
	}
}










