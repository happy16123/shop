package com.myshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myshop.domain.MailDTO;
import com.myshop.service.MailService;
import com.myshop.util.CertNumber;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class MailController {
	
	@Setter(onMethod_ = @Autowired)
	private MailService mailService;
	
	@PostMapping(value = "/mail/certification")
	public ResponseEntity<String> certify(MailDTO data) {
		log.info(data);

		CertNumber number = new CertNumber();
		String cert = number.executeCert();
		data.setContent(cert);
		mailService.certifyMail(data);
		return null;
//		return check == true ? new ResponseEntity<String>(data.getContent(), HttpStatus.OK)
//				: new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
}
