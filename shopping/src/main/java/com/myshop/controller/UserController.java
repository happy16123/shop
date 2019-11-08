package com.myshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.myshop.domain.UserVO;
import com.myshop.service.UserService;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RestController
@AllArgsConstructor
@Log4j
public class UserController {
	
	@Setter(onMethod_ = @Autowired)
	private UserService userService;
	
	@GetMapping(value = "/user/new")
	public ModelAndView signupView() {
		ModelAndView mav = new ModelAndView("user/signup");
		return mav;
	}
	
	@PostMapping(value = "/user/new", consumes = "application/json")
	public ResponseEntity<String> register(@RequestBody UserVO vo) {
		log.info("register : " + vo);
		
		return userService.userRegister(vo) == 1 ?
				new ResponseEntity<String>(HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
