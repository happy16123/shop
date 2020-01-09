package com.myshop.service;

import com.myshop.domain.MailDTO;

public interface MailService {
	
	public boolean certifyMail(MailDTO data);
	public boolean findId(String email, String name);
}
