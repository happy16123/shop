package com.myshop.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.myshop.security.CustomAuthenticationProvider;
import com.myshop.security.CustomUserDetailsService;

import lombok.Setter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Setter(onMethod_ = @Autowired)
	private DataSource dataSource;
	
	@Setter(onMethod_ = @Autowired)
	private CustomUserDetailsService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/user/new").permitAll()
			.antMatchers("/user/page").hasAuthority("ROLE_USER");
			
		
		http.formLogin()
			.loginPage("/user/login");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CustomAuthenticationProvider authenticationProvider() {
		return new CustomAuthenticationProvider();
	}
}
