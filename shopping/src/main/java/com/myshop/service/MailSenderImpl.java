package com.myshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import com.myshop.util.MailUtil;

import lombok.Setter;

public class MailSenderImpl implements MailSender{
	
	@Setter(onMethod_ = @Autowired)
	private JavaMailSender mailSender;
	
	@Override
	public void sendMail() throws Exception {
		MailUtil mailUtil = new MailUtil(mailSender);
	}

}
