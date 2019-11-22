package com.myshop.chat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class CustomSocketHandler extends TextWebSocketHandler {

	private static List<WebSocketSession> list = new ArrayList<WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		list.add(session);
		log.warn(session.getId() + "입장");
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.warn(session.getId() + " : " + message.getPayload());
		
		for(WebSocketSession se : list) {
			se.sendMessage(new TextMessage(session.getId() + " : " + message.getPayload()));
		}
	}	
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		list.remove(session);
		log.warn(session.getId() + "퇴장");
	}
}
