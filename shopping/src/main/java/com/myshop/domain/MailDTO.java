package com.myshop.domain;

import lombok.Data;

@Data
public class MailDTO {
	
	private String sender;
	private String receiver;
	private String title;
	private String content;
}
