package com.myshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping(value = "/mail/{email:.+}/certification",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MailDTO> certify(@PathVariable("email") String email) {
		log.info(email);
		
		MailDTO data = new MailDTO();
		data.setReceiver(email);
		CertNumber number = new CertNumber();
		String cert = number.executeCert();
		data.setContent(cert);
		boolean check = mailService.certifyMail(data);
		log.info("check : " + check);
		return check == true ? new ResponseEntity<MailDTO>(data, HttpStatus.OK) 
				: new ResponseEntity<MailDTO>(data, HttpStatus.BAD_REQUEST);
	}
}