package com.myshop.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.extern.log4j.Log4j;

public class CustomLoginFailureHandler implements AuthenticationFailureHandler{
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String msg = null;
		
		if(exception instanceof BadCredentialsException) {
			msg = exception.getMessage();
		} else if(exception instanceof InternalAuthenticationServiceException) {
			msg = exception.getMessage();
		} else if(exception instanceof UsernameNotFoundException) {
			msg = exception.getMessage();
		}
		
		response.sendRedirect("/user/signin?error=" + msg);
		
	}
}
