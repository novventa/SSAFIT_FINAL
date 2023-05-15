package com.ssafit.model.service;

import java.util.List;

import com.ssafit.model.dto.Review;

public interface ReviewService {

	public List<Review> getAllReviews(int videoId);
	public int writeReview(Review review);
	public int modifyReview(Review review);
	public int removeReview(int idx);
	public Review getReview(int idx);
	
}
