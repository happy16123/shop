package com.myshop.mapper;

import com.myshop.domain.UserVO;

public interface UserMapper {
	
	public int register(UserVO vo);
	public UserVO signIn(String username);
	public String check(String username);
	public UserVO getInfo(String username);
}
