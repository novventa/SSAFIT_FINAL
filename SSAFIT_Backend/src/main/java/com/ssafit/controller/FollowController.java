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

import com.ssafit.exception.CustomException;
import com.ssafit.model.dto.ErrorCode;
import com.ssafit.model.dto.Follow;
import com.ssafit.model.dto.User;
import com.ssafit.model.service.FollowService;
import com.ssafit.model.service.UserService;

@RestController
@RequestMapping("/api-follow")
public class FollowController {

	private static final String SUCCESS = "success";
	
	@Autowired
	FollowService followService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("find")
	public ResponseEntity<?> followFind(String yourNickname) throws CustomException {
		List<User> list = userService.findUsersByNickname(yourNickname);
		if(list == null) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		else if(list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	@PostMapping("add")
	public ResponseEntity<?> followAdd(Follow follow) throws CustomException {
		int result = followService.addFollow(follow);
		if(result == 0) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}
	
	@GetMapping("list/{myIdx}")
	public ResponseEntity<?> followList(@PathVariable int myIdx) throws CustomException {
		List<Follow> list = followService.findAllFollows(myIdx);
		if(list == null) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		if(list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Follow>>(list, HttpStatus.OK);
	}
	
	@GetMapping("details/{idx}")
	public ResponseEntity<?> followDetails(@PathVariable int idx) throws CustomException {
		Follow follow = followService.findFollow(idx);
		if(follow == null) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Follow>(follow, HttpStatus.OK);
	}
	
	@DeleteMapping("remove/{idx}")
	public ResponseEntity<?> followRemove(@PathVariable int idx) throws CustomException {
		int result = followService.removeFollow(idx);
		if(result == 0) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}
