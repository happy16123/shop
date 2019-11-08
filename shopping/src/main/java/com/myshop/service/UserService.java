package com.myshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myshop.domain.UserVO;
import com.myshop.mapper.UserMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class UserService implements UserDetailsService {
	
	@Setter(onMethod_ = @Autowired)
	private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return null;
	}
	
	public int userRegister(UserVO vo) {
		log.info("유저 등록 : " + vo);
		return userMapper.register(vo);
	}
	
}
