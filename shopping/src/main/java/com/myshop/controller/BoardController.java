package com.myshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myshop.domain.BoardVO;
import com.myshop.service.BoardService;

import lombok.Setter;

@RestController
@RequestMapping("/board")
public class BoardController {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService boardService;
	
	@GetMapping(value = "/{page}")
	public ResponseEntity<List<BoardVO>> getList(@PathVariable("page") int page) {
		return null;
	}
	
	@GetMapping(value = "/{page}/{bno}")
	public ResponseEntity<BoardVO> getDetail(@PathVariable("page") int page, @PathVariable("bno") long bno){
		return null;
	}
	
	@PostMapping(value = "/new", consumes = "application/json")
	public ResponseEntity<String> create(@RequestBody BoardVO vo){
		return null;
	}
	
	@PatchMapping(value = "/{bno}", consumes = "application/json")
	public ResponseEntity<String> modify(@RequestBody BoardVO vo, @PathVariable("bno") long bno){
		return null;
	}
	
	@DeleteMapping(value = "/{bno}")
	public ResponseEntity<String> remove(@PathVariable("bno") long bno){
		return null;
	}
}
