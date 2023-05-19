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

import com.ssafit.model.dto.Likes;
import com.ssafit.model.service.LikesService;

@RestController
@RequestMapping("/api-like")
public class LikesController {
	
	@Autowired
	LikesService likesService;
	
	@PostMapping("add")
	public ResponseEntity<?> likeAdd(Likes likes) {
		int result = likesService.addLikes(likes);
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}
	
	@GetMapping("list-user/{userIdx}")
	public ResponseEntity<?> likeListByUser(@PathVariable int userIdx) {
		List<Likes> list = likesService.findAllLikesByUser(userIdx);
		if(list == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Likes>>(list, HttpStatus.OK);
	}
	
	@GetMapping("list-video/{videoIdx}")
	public ResponseEntity<?> likeListByVideo(@PathVariable int videoIdx) {
		List<Likes> list = likesService.findAllLikesByVideo(videoIdx);
		if(list == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Likes>>(list, HttpStatus.OK);
	}
	
	
	@GetMapping("details/{userIdx}/{videoIdx}")
	public ResponseEntity<?> likeDetails(@PathVariable int videoIdx, @PathVariable int userIdx) {
		Likes like = likesService.findLikes(new Likes(videoIdx, userIdx));
		if(like == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
		}
		return new ResponseEntity<Likes>(like, HttpStatus.OK);
	}
	
	@DeleteMapping("remove/{idx}")
	public ResponseEntity<?> likeRemove(@PathVariable int idx) {
		int result = likesService.removeLikes(idx);
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
