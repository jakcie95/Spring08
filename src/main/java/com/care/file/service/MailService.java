package com.care.file.service;

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
			MimeMessageHelper mm = new MimeMessageHelper(message,true,"UTF-8");
			mm.setSubject(subject);//제목
			mm.setTo(to);// 보낼사람
			mm.setText(body);//내용
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		public void sendMail2(String to, String subject, String body) {
			MimeMessage message = mailSender.createMimeMessage();
			try {
				MimeMessageHelper mm = new MimeMessageHelper(message,true,"UTF-8");
				mm.setSubject(subject);//제목
				mm.setTo(to);// 보낼사람
				mm.setText(body, true);//내용
				mailSender.send(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
		public void auth(HttpServletRequest request) {
			HttpSession session = request.getSession();
			String userid ="jungho";
			String userkey = rand();
			session.setAttribute(userid, userkey);
			
			String body = "<h3>안녕하세요 인증입니다</h3><hr>";
			body+= "<h5>"+userid+"님</h5>";
			body+="<a href='http://localhost:8085"+request.getContextPath()+"/auth_check?userid="+userid+"&userkey="+userkey+"'>인증하기</a>";
			sendMail2("jakcie951224@gmail.com","이메일 인증", body);
		}
		private String rand() {//인증키 생산 메소드
			Random rad = new Random();
			String str="";
			int num;
			while( str.length() != 20) {//문자조합 20개 이상일시 while문을 빠져나감
				num = rad.nextInt(75) + 48;//0-74 = +48 = 48~122 아스키코드 표 참조
				if((num>=48 && num<=57)||(num>=65 && num<=90)||(num>=97 && num<=122)) {
					str +=(char)num;
				}
			}
			return str;
		}
}









