package com.myshop.mapper;

import java.util.List;

import com.myshop.domain.FileVO;

public interface FileMapper {
	
	public int insert(FileVO vo);
	public List<FileVO> findByBno(Long bno);
	public int delete(String uuid);
	public int deleteAll(Long bno);
}