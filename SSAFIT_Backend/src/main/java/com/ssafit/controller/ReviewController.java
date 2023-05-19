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
	
	@GetMapping("{vidIdx}")
	public ResponseEntity<?> reviewList(@PathVariable int vidIdx) {
		
		List<Review> list = reviewService.findAllReviews(vidIdx);

		return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
	}
	
	@PostMapping("{vidIdx}")
	public ResponseEntity<?> reviewAdd(@PathVariable int vidIdx, Review review){
		int result = reviewService.addReview(review);
		
		if(result == 0)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<Integer>(result,HttpStatus.OK);
	}
	
	@PutMapping("{revIdx}")
	public ResponseEntity<?> reviewModify(@PathVariable int revIdx, Review review){
		int result = reviewService.modifyReview(review);
		
		if(result == 0)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<Integer>(result,HttpStatus.OK);
	}
	
	@DeleteMapping("{revIdx}")
	public ResponseEntity<?> reviewRemove(@PathVariable int revIdx){
		int result = reviewService.removeReview(revIdx);
		
		if(result == 0)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<Integer>(result,HttpStatus.OK);
	}
	
}
