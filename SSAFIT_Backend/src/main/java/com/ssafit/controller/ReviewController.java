package com.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.model.dto.Review;
import com.ssafit.model.service.ReviewService;
import com.ssafit.model.service.VideoService;

@RestController
@RequestMapping("api-review")
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@Autowired
	VideoService videoService;
	
	@PostMapping("add")
	public ResponseEntity<?> reviewAdd(Review review){
		int result = reviewService.addReview(review);
		
		if(result == 0)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<Integer>(result,HttpStatus.OK);
	}
	
	@GetMapping("list/{videoIdx}")
	public ResponseEntity<?> reviewList(@PathVariable int videoIdx) {
		
		List<Review> list = reviewService.findAllReviews(videoIdx);

		return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
	}
	
	
	@PutMapping("modify")
	public ResponseEntity<?> reviewModify(Review review){
		int result = reviewService.modifyReview(review);
		
		if(result == 0)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<Integer>(result,HttpStatus.OK);
	}
	
	@DeleteMapping("remove/{idx}")
	public ResponseEntity<?> reviewRemove(@PathVariable int idx){
		int result = reviewService.removeReview(idx);
		
		if(result == 0)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<Integer>(result,HttpStatus.OK);
	}
	
}
