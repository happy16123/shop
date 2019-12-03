package com.myshop.service;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.myshop.domain.MailDTO;
import com.myshop.util.MailUtil;

import lombok.Setter;

@Service
public class MailServiceImpl implements MailService{
	
	@Setter(onMethod_ = @Autowired)
	private JavaMailSender mailSender;

	@Override
	public boolean certifyMail(MailDTO data){
		
		try {
			MailUtil mail = new MailUtil(mailSender);
			mail.setSubject("회원가입 이메일 인증");
			mail.setText("인증번호 : " + data.getContent());
			mail.setTo(data.getReceiver());
			mail.setFrom("hskim1239@gmail.com");
			
			mail.send();
			return true;
		} catch(MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}

}