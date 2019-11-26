package com.myshop.domain;

import lombok.Data;

@Data
public class ChatMessage {
	
	private String roomId;
	private String sender;
	private String message;
	
}
