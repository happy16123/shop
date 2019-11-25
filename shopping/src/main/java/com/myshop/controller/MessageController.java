package com.myshop.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class MessageController {
	
	@MessageMapping("/echo")
	@SendTo("/topic/msg")
	public String echo(String data) throws Exception {
		log.info("data : " + data);
		return data;
	}
}
