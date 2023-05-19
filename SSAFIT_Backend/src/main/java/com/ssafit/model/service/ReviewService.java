package com.ssafit.model.service;

import java.util.List;

import com.ssafit.model.dto.Review;

public interface ReviewService {

	public List<Review> findAllReviews(int videoId);
	public int addReview(Review review);
	public int modifyReview(Review review);
	public int removeReview(int idx);
	public Review findReview(int idx);
	
}
