package com.myshop.repo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.myshop.domain.ChatRoom;

@Repository
public class ChatRoomRepository {
	
	private Map<String, ChatRoom> roomMap;
	
	@PostConstruct
	public void init() {
		roomMap = new LinkedHashMap<String, ChatRoom>();
	}
	
	public List<ChatRoom> findAllRoom(){
		List<ChatRoom> rooms = new ArrayList<>(roomMap.values());
		Collections.reverse(rooms);
		return rooms;
	}
	
	public ChatRoom findRoomById(String id) {
		return roomMap.get(id);
	}
	
	public ChatRoom createRoom(String name) {
		ChatRoom room = ChatRoom.create(name);
		roomMap.put(room.getId(), room);
		return room;
	}
}
