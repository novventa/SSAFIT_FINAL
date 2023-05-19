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

import com.ssafit.model.dto.Like;
import com.ssafit.model.service.LikeService;

@RestController
@RequestMapping("/api-like")
public class LikeController {
	
	@Autowired
	LikeService likeService;
	
	@PostMapping()
	public ResponseEntity<?> likeAdd(Like like) {
		int result = likeService.addLike(like);
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}
	
	@GetMapping("{userIdx}")
	public ResponseEntity<?> likeListByUser(@PathVariable int userIdx) {
		List<Like> list = likeService.findAllLikesByUser(userIdx);
		if(list == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Like>>(list, HttpStatus.OK);
	}
	
	@GetMapping("{videoIdx}")
	public ResponseEntity<?> likeListByVideo(@PathVariable int videoIdx) {
		List<Like> list = likeService.findAllLikesByVideo(videoIdx);
		if(list == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Like>>(list, HttpStatus.OK);
	}
	
	
	@GetMapping("{userIdx}/{videoIdx}")
	public ResponseEntity<?> likeDetails(@PathVariable int videoIdx, @PathVariable int userIdx) {
		Like like = likeService.findLike(new Like(videoIdx, userIdx));
		if(like == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
		}
		return new ResponseEntity<Like>(like, HttpStatus.OK);
	}
	
	@DeleteMapping("{idx}")
	public ResponseEntity<?> likeRemove(@PathVariable int idx) {
		int result = likeService.removeLike(idx);
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
