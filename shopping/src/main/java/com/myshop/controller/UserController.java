package com.myshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.myshop.domain.UserVO;
import com.myshop.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@Log4j
public class UserController {

	private UserService userService;
	
	@GetMapping("/user/create")
	public void register() {
		UserVO vo = new UserVO();
		vo.setId("test");
		vo.setPassword("{noop}test");
		vo.setEmail("test@test.com");
		vo.setName("tester");
		vo.setAuthority("ROLE_USER");
		vo.setAddress("ww");
		log.info("register : " + vo);
		userService.userRegister(vo);
	}
}
