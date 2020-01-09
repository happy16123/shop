package com.myshop.mapper;

import com.myshop.domain.BoardVO;

public interface BoardMapper {
	
	public BoardVO allList(); 
	public BoardVO detail(long bno); 
	public int insert(BoardVO vo);
	public int delete(long bno);
	public int update(BoardVO vo);
}
