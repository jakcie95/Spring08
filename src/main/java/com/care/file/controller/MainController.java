package com.care.file.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.care.file.service.MailService;

@Controller
public class MainController {
	@Autowired MailService ms;
	
	@GetMapping("sendmail")
	public void sendmail(HttpServletRequest req,
						HttpServletResponse res) throws Exception{
		ms.sendMail("jakcie951224@gmail.com",
					"테스트메일(제목)","안녕하세요(내용)");
		
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("메일을 전송하였습니다");
		
	}
	@GetMapping("sendmail2")
	public void sendmail2(HttpServletRequest req,
						HttpServletResponse res) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("<html><body>");
		sb.append("<a href='https://www.naver.com'");
		sb.append("<img src='http://localhost:8085/file/download?file=20220803201612add.png' width='100px' height='100px;'>");
		sb.append("</a></body></html>");
		String str = sb.toString();
		ms.sendMail2("jakcie951224@gmail.com",
					"광고(제목)",str);
		
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("메일을 전송하였습니다");
		
	}
	@GetMapping("auth_form")
	public String authForm() {
		return "auth_form";
	}
	@GetMapping("auth")
	public String auth(HttpServletRequest request) {
		ms.auth(request);
		return "redirect:https://www.google.com";
	}
	@GetMapping("auth_check")
	public String auth_check(String userid, String userkey,
							HttpSession session) {
		String key=(String)session.getAttribute(userid);
		if(key != null) {
			if(key.equals(userkey)) {
				session.setAttribute("userid", userid);
			}
		}
		return "redirect:auth_form";
	}
}














