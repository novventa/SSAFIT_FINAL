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

import com.ssafit.model.dto.Follow;
import com.ssafit.model.service.FollowService;

@RestController
@RequestMapping("/api-follow")
public class FollowController {

	@Autowired
	FollowService followService;
	
	@PostMapping("add")
	public ResponseEntity<?> followAdd(Follow follow) {
		int result = followService.addFollow(follow);
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}
	
	@GetMapping("list/{myIdx}")
	public ResponseEntity<?> followList(@PathVariable int myIdx) {
		List<Follow> list = followService.findAllFollows(myIdx);
		if(list == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Follow>>(list, HttpStatus.OK);
	}
	
	@GetMapping("details/{myIdx}/{yourIdx}")
	public ResponseEntity<?> followDetails(@PathVariable int myIdx, @PathVariable int yourIdx) {
		Follow follow = followService.findFollow(new Follow(myIdx, yourIdx));
		if(follow == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
		}
		return new ResponseEntity<Follow>(follow, HttpStatus.OK);
	}
	
	@DeleteMapping("remove/{idx}")
	public ResponseEntity<?> followRemove(@PathVariable int idx) {
		int result = followService.removeFollow(idx);
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
