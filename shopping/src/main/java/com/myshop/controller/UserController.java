package com.myshop.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.myshop.domain.UserVO;
import com.myshop.security.CustomUserDetailsService;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RestController
@AllArgsConstructor
@Log4j
public class UserController {
	
	@Setter(onMethod_ = @Autowired)
	private CustomUserDetailsService userService;
	
	@GetMapping(value = "/user/new")
	public ModelAndView signupView() {
		ModelAndView mav = new ModelAndView("user/signup");
		return mav;
	}
	
	//@PostMapping(value = "/user/new", consumes = "application/json")
	@PostMapping(value = "/user/new")
	public ResponseEntity<String> register(UserVO vo) {
		log.info("register : " + vo);
		return userService.userRegister(vo) == 1 ?
				new ResponseEntity<String>(HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/user/signin")
	public ModelAndView loginView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/signin");
		return mav;
		
	}
	
	@GetMapping(value = "/user/logout")
	public void logout() {
	}
	
	@GetMapping(value = "/user/success")
	@PreAuthorize("isAuthenticated()")
	public ModelAndView loginSuccess() {
		ModelAndView mav = new ModelAndView("user/success");
		return mav;
	}
	
	@GetMapping(value = "/user/privacy")
	@PreAuthorize("isAuthenticated()")
	public ModelAndView check() {
		ModelAndView mav = new ModelAndView("user/privacy");
		return mav;
	}
	
	@PostMapping(value = "/user/privacy", 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@PostMapping(value = "/user/privacy")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<UserVO> privacy(@RequestBody Map<String, String> password) {
		log.info(password);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String pass = password.get("password"); 
		log.info(auth.getName() + " " + pass);
		UserVO vo = null;
		if(userService.passwordCheck(auth.getName(), pass)) {
			vo = userService.getData(auth.getName());
		}

		return vo != null ?
				new ResponseEntity<UserVO>(vo, HttpStatus.OK) : new ResponseEntity<UserVO>(vo, HttpStatus.BAD_REQUEST);
	}
}
