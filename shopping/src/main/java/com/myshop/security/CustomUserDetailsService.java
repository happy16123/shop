package com.myshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myshop.domain.UserVO;
import com.myshop.mapper.UserMapper;

import lombok.Setter;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Setter(onMethod_ = @Autowired)
	private UserMapper userMapper;
	
	@Setter(onMethod_ = @Autowired)
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserVO vo = userMapper.signIn(username);
		
		if(vo == null) {
			throw new UsernameNotFoundException(username);
		}
		
		CustomUserDetails data = new CustomUserDetails();
		data.setId(vo.getId());
		data.setPassword(vo.getPassword());
		data.setAddress(vo.getAddress());
		data.setAuthority(vo.getAuthority());
		data.setEmail(vo.getEmail());
		data.setName(vo.getName());
		
		return data;
	}
	
	public UserVO getData(String username) { 
		return userMapper.getInfo(username);
	}
	
	public int userRegister(UserVO vo) {
		vo.setPassword(passwordEncoder.encode(vo.getPassword()));
		vo.setAuthority("ROLE_USER");
		
		return userMapper.register(vo);
	}
	
	public boolean passwordCheck(String username, String password) {
		boolean result = false;
		String value = userMapper.check(username);
		if(passwordEncoder.matches(password, value)) {
			result = true;
		}
		return result;
	}
	

}
