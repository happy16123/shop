package com.myshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myshop.domain.BoardVO;
import com.myshop.mapper.BoardMapper;

import lombok.Setter;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Setter(onMethod_ = {@Autowired})
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> getAllList() {
		return boardMapper.allList();
	}
	
	@Override
	public BoardVO getDetail(long bno) {
		return boardMapper.detail(bno);
	}
	
	@Override
	public int modify(BoardVO vo) {
		return boardMapper.update(vo);
	}
	
	@Override
	public int register(BoardVO vo) {
		return boardMapper.insert(vo);
	}
	
	@Override
	public int remove(long bno) {
		return boardMapper.delete(bno);
	}
	
}
