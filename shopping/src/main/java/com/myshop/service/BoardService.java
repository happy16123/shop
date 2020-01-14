package com.myshop.service;

import java.util.List;

import com.myshop.domain.BoardVO;
import com.myshop.domain.Criteria;

public interface BoardService {
	
	public List<BoardVO> getAllList(Criteria cri);
	public BoardVO getDetail(long bno);
	public int register(BoardVO vo);
	public int remove(long bno);
	public int modify(BoardVO vo);
	
}
