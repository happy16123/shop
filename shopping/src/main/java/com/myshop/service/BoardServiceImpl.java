package com.myshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myshop.domain.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	
	
	@Override
	public List<BoardVO> getAllList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public BoardVO getDetail(long bno) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int modify(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int register(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int remove(long bno) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public BoardServiceImpl() {
		// TODO Auto-generated constructor stub
	}
}
