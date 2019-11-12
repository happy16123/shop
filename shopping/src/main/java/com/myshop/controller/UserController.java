package com.myshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping(value = "/user/login")
	public ModelAndView loginView() {
		ModelAndView mav = new ModelAndView("user/signin");
		return mav;
	}
	
	@GetMapping(value = "/user/privacy")
	public ModelAndView check() {
		ModelAndView mav = new ModelAndView("user/privacy");
		return mav;
	}
	
	//@PostMapping(value = "/user/privacy", consumes = "application/json")
	@PostMapping(value = "/user/privacy")
	public ResponseEntity<UserVO> privacy(String password) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		log.info(auth.getName() + " " + password);
		boolean check = userService.passwordCheck(auth.getName(), password);
		log.info(check);
		/*log.info(password);
		log.info((String)auth.getCredentials());
		
		if(!passwordEncoder.matches(password, (String)auth.getCredentials())) {
			log.info("비밀번호 맞지않음");
			return new ResponseEntity<UserVO>(HttpStatus.BAD_REQUEST);
		}
		CustomUserDetails user = (CustomUserDetails)auth.getDetails();
		UserVO vo = new UserVO();
		vo.setAddress(user.getAddress());
		vo.setEmail(user.getEmail());
		vo.setRegDate(user.getRegDate());
		vo.setName(user.getName());
		log.info(vo);
		return new ResponseEntity<UserVO>(vo, HttpStatus.OK);*/
		return null;
	}
}
