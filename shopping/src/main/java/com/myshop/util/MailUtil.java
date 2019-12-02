package com.myshop.util;

import java.io.UnsupportedEncodingException;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailUtil {
	
	private JavaMailSender mail;
	private MimeMessage msg;
	private MimeMessageHelper msgHelper;
	
	public MailUtil(JavaMailSender mail) throws MessagingException {
		this.mail = mail;
		this.msg = this.mail.createMimeMessage();
		this.msgHelper = new MimeMessageHelper(msg, true, "utf-8");
	}
	
	public void setSubject(String subject) throws MessagingException{
		msgHelper.setSubject(subject);
	}
	
	public void setText(String htmlContent) throws MessagingException{
		msgHelper.setText(htmlContent, true);
	}
	
	public void setFrom(String email, String name) throws UnsupportedEncodingException, MessagingException{
		msgHelper.setFrom(email, name);
	}
	
	public void setTo(String email) throws MessagingException{
		msgHelper.setTo(email);
	}
	
	public void addInline(String contentId, DataSource dataSource) throws MessagingException{
		msgHelper.addInline(contentId, dataSource);
	}
	
	public void send() {
		mail.send(msg);
	}
}
