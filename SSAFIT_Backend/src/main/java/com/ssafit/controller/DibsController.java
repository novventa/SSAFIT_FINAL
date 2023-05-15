package com.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.model.dto.Dibs;
import com.ssafit.model.service.DibsService;

@RestController
@RequestMapping("/api-dibs")
public class DibsController {
	
	@Autowired
	DibsService dibsService;
	
	@PostMapping()
	public ResponseEntity<?> addDibs(Dibs dibs) {
		int result = dibsService.addDibs(dibs);
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}
	
	@DeleteMapping()
	public ResponseEntity<?> removeDibs(Dibs dibs) {
		int result = dibsService.removeDibs(dibs);
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping("{nickname}")
	public ResponseEntity<?> getDibsList(@PathVariable String nickname) {
		List<Dibs> list = dibsService.getDibsList(nickname);
		if(list == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Dibs>>(list, HttpStatus.OK);
	}
//	@GetMapping("{videoId}")
//	public ResponseEntity<?> getDibs(@PathVariable String videoId) {
//		Dibs dibs;
//		dibs.setNickname(nickname);
//		Dibs dibs = dibsService.getDibs();
//		if(dibs == null) {
//			
//		}
//	}
}
