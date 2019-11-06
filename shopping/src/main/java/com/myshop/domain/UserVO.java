package com.myshop.domain;

import java.util.Date;

import lombok.Data;

@Data
public class UserVO{

	private String id;
	private String password;
	private String name;
	private String email;
	private String address;
	private Date regDate;
	private String authority;
	
}
