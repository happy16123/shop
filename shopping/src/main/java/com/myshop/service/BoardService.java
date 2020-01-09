package com.myshop.service;

import java.util.List;

import com.myshop.domain.BoardVO;

public interface BoardService {
	
	public List<BoardVO> getAllList();
	public BoardVO getDetail(long bno);
	public int register(BoardVO vo);
	public int remove(long bno);
	public int modify(BoardVO vo);
	
}
