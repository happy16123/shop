package com.myshop.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Setter;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Setter(onMethod_ = @Autowired)
	private CustomUserDetailsService userService;
	
	@Setter(onMethod_ = @Autowired)
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String)authentication.getCredentials();
		
		CustomUserDetails user = null;
		Collection<? extends GrantedAuthority> authorities = null;
		
		try {
			user = (CustomUserDetails)userService.loadUserByUsername(username);
			
			if(!passwordEncoder.matches(password, user.getPassword())){
				throw new BadCredentialsException("비밀번호 불일치");
			}
			authorities = user.getAuthorities(); 
			
		} catch(UsernameNotFoundException e) {
			e.printStackTrace();
		} catch(BadCredentialsException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new UsernamePasswordAuthenticationToken(username, password, authorities);
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}
}
