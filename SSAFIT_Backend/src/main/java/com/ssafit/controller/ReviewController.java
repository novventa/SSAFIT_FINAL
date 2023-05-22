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

import com.ssafit.exception.CustomException;
import com.ssafit.model.dto.ErrorCode;
import com.ssafit.model.dto.Review;
import com.ssafit.model.service.ReviewService;
import com.ssafit.model.service.VideoService;

@RestController
@RequestMapping("api-review")
public class ReviewController {

	private static final String SUCCESS = "success";

	@Autowired
	ReviewService reviewService;
	
	@Autowired
	VideoService videoService;
	
	@PostMapping("add")
	public ResponseEntity<?> reviewAdd(Review review) throws CustomException{
		int result = reviewService.addReview(review);
		
		if(result == 0) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@GetMapping("list/{videoIdx}")
	public ResponseEntity<?> reviewList(@PathVariable int videoIdx) throws CustomException {
		
		List<Review> list = reviewService.findAllReviews(videoIdx);
		if(list == null) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		if(list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
	}
	
	@GetMapping("list-user/{userIdx}")
	public ResponseEntity<?> reviewListByUserIdx(@PathVariable int userIdx) throws CustomException {
		
		List<Review> list = reviewService.findAllReviewsByUserIdx(userIdx);
		if(list == null) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		if(list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
	}
	
	
	@PutMapping("modify")
	public ResponseEntity<?> reviewModify(Review review) throws CustomException{
		int result = reviewService.modifyReview(review);
		
		if(result == 0) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@DeleteMapping("remove/{idx}")
	public ResponseEntity<?> reviewRemove(@PathVariable int idx) throws CustomException{
		int result = reviewService.removeReview(idx);
		
		if(result == 0) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
}
