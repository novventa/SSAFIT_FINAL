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
	public ResponseEntity<?> addLike(Like like) {
		int result = likeService.addLike(like);
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}
	
	@DeleteMapping()
	public ResponseEntity<?> removeLike(int idx) {
		int result = likeService.removeLike(idx);
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping("{nickname}")
	public ResponseEntity<?> getLikeList(@PathVariable String nickname) {
		List<Like> list = likeService.getLikeList(nickname);
		if(list == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Like>>(list, HttpStatus.OK);
	}
//	@GetMapping("{videoId}")
//	public ResponseEntity<?> getLike(@PathVariable String videoId) {
//		Like Like;
//		Like.setNickname(nickname);
//		Like Like = LikeService.getLike();
//		if(Like == null) {
//			
//		}
//	}
}
