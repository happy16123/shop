package com.myshop.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.myshop.security.CustomLoginFailureHandler;
import com.myshop.security.CustomAuthenticationProvider;
import com.myshop.security.CustomLoginSuccessHandler;
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
		http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers("/user/new").permitAll()
			.antMatchers("/user/page").hasAuthority("ROLE_USER")
			.antMatchers("/user/privacy").access("hasRole('ROLE_USER')")
			.antMatchers("/chat/**").access("hasRole('ROLE_USER')");
//			.antMatchers("/board/**").access("hasRole('ROLE_USER')");
		
		
		http.formLogin()
			.loginPage("/user/signin")
			.loginProcessingUrl("/user/login")
			.successHandler(loginSuccessHandler())
			.failureHandler(loginFailureHandler());
		
		http.logout()
			.logoutUrl("/user/logout")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSION_ID");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	   super.configure(web);
	   web.ignoring().antMatchers("/assest/**","/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CustomAuthenticationProvider authenticationProvider() {
		return new CustomAuthenticationProvider();
	}
	
	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public AuthenticationFailureHandler loginFailureHandler() {
		return new CustomLoginFailureHandler();
	}
}
