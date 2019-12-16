package com.myshop.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfig  {
	
	@Value("${user}")
	private String user;
	
	@Value("${password}")
	private String password;
	
	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mail = new JavaMailSenderImpl();
		mail.setHost("smtp.gmail.com");
		mail.setPort(587);
		mail.setUsername(user);
		mail.setPassword(password);
		
		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.debug", "true");
		
		mail.setJavaMailProperties(properties);
		
		return mail;
	}
	
}
