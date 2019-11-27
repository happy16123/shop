package com.myshop.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.myshop.domain.ChatMessage;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class MessageController {
	
	@Setter(onMethod_ = @Autowired)
	private SimpMessagingTemplate temp;
	
	@MessageMapping("/chat/message")
	public void message(ChatMessage msg, Principal principal) {
//		log.info(msg);
//		log.info(principal);
		msg.setSender(principal.getName());
		temp.convertAndSend("/topic/chat/room/" + msg.getRoomId(), msg);
	}
	
	@MessageMapping("/chat/join")
	public void join(ChatMessage msg, Principal principal) {
//		log.info(msg);
//		log.info(principal);
		msg.setSender(principal.getName());
		msg.setMessage("[알림] " + principal.getName() + " 입장");
		temp.convertAndSend("/topic/chat/room/" + msg.getRoomId(), msg);
	}
	
	/*@MessageMapping("/echo")
	@SendTo("/topic/msg")
	public String echo(String data) throws Exception {
		log.info("data : " + data);
		return data;
	}
	
	@MessageMapping("/out")
	@SendTo("/topic/out")
	public String outUser(String data) throws Exception{
		return data + " 입장";
	}
	
	@MessageMapping("/in")
	@SendTo("/topic/in")
	public String inUser(String data) throws Exception{
		return data + " 나감";
	}*/
}
