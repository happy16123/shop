package com.myshop.mapper;

import com.myshop.domain.UserVO;

public interface UserMapper {
	
	public int register(UserVO vo);
	public UserVO signIn(String username);
	public String passwordCheck(String username);
	public UserVO getInfo(String username);
	public int idCheck(String username);
}
