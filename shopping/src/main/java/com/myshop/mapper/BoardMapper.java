package com.myshop.mapper;

import java.util.List;

import com.myshop.domain.BoardVO;

public interface BoardMapper {
	
	public List<BoardVO> allList(); 
	public BoardVO detail(long bno); 
	public int insert(BoardVO vo);
	public int delete(long bno);
	public int update(BoardVO vo);
}
