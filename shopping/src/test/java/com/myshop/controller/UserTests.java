package com.myshop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.myshop.domain.UserVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		com.myshop.config.RootConfig.class,
		com.myshop.config.ServletConfig.class,
		com.myshop.config.SecurityConfig.class})
@Log4j
public class UserTests {
	
	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext ctx;
	
	private MockMvc mock;
	
	@Before
	public void setup() {
		this.mock = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testSignup() throws Exception{
		UserVO vo = new UserVO();
		vo.setId("user1");
		vo.setPassword("user1");
		vo.setEmail("user1");
		vo.setAddress("user1");
		vo.setName("user1");
		vo.setAuthority("ROLE_USER");
		
		String json = new Gson().toJson(vo);
		
		log.info(json);
		
		mock.perform(post("/user/new")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().is(200));
	}
	
	@Test
	public void testSignin() throws Exception{
		String username = "user1";
		String password = "user1";
		
		mock.perform(post("/user/login")
				.param("username", username)
				.param("password", password))
			.andExpect(status().is(200));
		
	}
}
