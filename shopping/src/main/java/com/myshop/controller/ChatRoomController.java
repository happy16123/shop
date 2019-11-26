package com.myshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.myshop.repo.ChatRoomRepository;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("/chat")
public class ChatRoomController {

	@Setter(onMethod_ = @Autowired)
	private ChatRoomRepository repo;
	
	@GetMapping(value = "/room")
	public ModelAndView listRoom() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", repo.findAllRoom());
		mav.setViewName("chat/room");
		log.info(mav);
		return mav;
	}
	
	@PostMapping(value = "/room")
	public RedirectView createRoom(String name) {
		log.info(repo.createRoom(name));
		return new RedirectView("/chat/room");
	}
}
