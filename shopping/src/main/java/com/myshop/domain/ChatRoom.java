package com.myshop.domain;

import java.util.UUID;

import lombok.Data;

@Data
public class ChatRoom {
	
	private String id;
	private String name;
	
	public static ChatRoom create(String name) {
		ChatRoom room = new ChatRoom();
		room.id = UUID.randomUUID().toString();
		room.name = name;
		return room;
	}
}
