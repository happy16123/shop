package com.myshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myshop.service.BoardService;

import lombok.Setter;

@RestController
@RequestMapping("/board")
public class BoardController {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService boardService;
	
	@GetMapping(value = "/list/{page}")
	public ResponseEntity<String> getList(@PathVariable("page") int page) {
		return null;
	}
}
