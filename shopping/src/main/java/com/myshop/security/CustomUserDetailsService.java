package com.myshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myshop.domain.UserVO;
import com.myshop.mapper.UserMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class CustomUserDetailsService implements UserDetailsService {
	
	@Setter(onMethod_ = @Autowired)
	private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("유저 아이디 : " + username);
		
		UserVO vo = userMapper.signIn(username);
		
		CustomUserDetails data = new CustomUserDetails();
		data.setId(vo.getId());
		data.setPassword(vo.getPassword());
		data.setAddress(vo.getAddress());
		data.setAuthority(vo.getAuthority());
		data.setEmail(vo.getEmail());
		data.setName(vo.getName());
		
		return data;
	}
	
	public int userRegister(UserVO vo) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		vo.setPassword(passwordEncoder.encode(vo.getPassword()));
		log.info("유저 등록 : " + vo);
		
		return userMapper.register(vo);
	}
	
}
