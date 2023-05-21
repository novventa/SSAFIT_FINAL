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
import com.ssafit.model.dto.Likes;
import com.ssafit.model.service.LikesService;

@RestController
@RequestMapping("/api-like")
public class LikesController {
	
	private static final String SUCCESS = "success";

	@Autowired
	LikesService likesService;
	
	@PostMapping("add")
	public ResponseEntity<?> likeAdd(Likes likes) throws CustomException {
		int result = likesService.addLikes(likes);
		if(result == 0) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}
	
	@GetMapping("list-user/{userIdx}")
	public ResponseEntity<?> likeListByUser(@PathVariable int userIdx) throws CustomException {
		List<Likes> list = likesService.findAllLikesByUser(userIdx);
		if(list == null) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		if(list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Likes>>(list, HttpStatus.OK);
	}
	
	@GetMapping("list-video/{videoIdx}")
	public ResponseEntity<?> likeListByVideo(@PathVariable int videoIdx) throws CustomException {
		List<Likes> list = likesService.findAllLikesByVideo(videoIdx);
		if(list == null) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		if(list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Likes>>(list, HttpStatus.OK);
	}
	
	
	@GetMapping("details/{idx}")
	public ResponseEntity<?> likeDetails(@PathVariable int idx) throws CustomException {
		Likes like = likesService.findLikes(idx);
		if(like == null) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Likes>(like, HttpStatus.OK);
	}
	
	@DeleteMapping("remove/{idx}")
	public ResponseEntity<?> likeRemove(@PathVariable int idx) throws CustomException {
		int result = likesService.removeLikes(idx);
		if(result == 0) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}
