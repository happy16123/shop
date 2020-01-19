package com.myshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.myshop.domain.Criteria;
import com.myshop.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/board")
@Log4j
public class BoardController {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService boardService;
	
	@GetMapping(value = "/{pageNum}")
	public ResponseEntity<List<BoardVO>> getList(@PathVariable("pageNum") int pageNum) {
		Criteria cri = new Criteria();
		cri.setPageNum(pageNum);
		return new ResponseEntity<List<BoardVO>>(boardService.getAllList(cri), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{page}/{bno}")
	public ResponseEntity<BoardVO> getDetail(@PathVariable("page") int page, @PathVariable("bno") long bno){
		return new ResponseEntity<BoardVO>(boardService.getDetail(bno), HttpStatus.OK);
	}
	
	@PostMapping(value = "/new", consumes = "application/json")
	public ResponseEntity<String> create(@RequestBody BoardVO vo){
		log.info("board create : " + vo);
		int result = boardService.register(vo);
		return result == 0 ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	@PatchMapping(value = "/{bno}", consumes = "application/json")
	public ResponseEntity<String> modify(@RequestBody BoardVO vo, @PathVariable("bno") long bno){
		vo.setBno(bno);
		log.info("board modify : " + vo);
		int result = boardService.modify(vo);
		return result == 0 ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping(value = "/{bno}")
	public ResponseEntity<String> remove(@PathVariable("bno") long bno){
		log.info("board delete : " + bno);
		int result = boardService.remove(bno);
		return result == 0 ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
}
