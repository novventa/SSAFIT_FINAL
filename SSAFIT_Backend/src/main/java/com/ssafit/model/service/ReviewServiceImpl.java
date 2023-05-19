package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.ReviewDao;
import com.ssafit.model.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao reviewDao;
	
	@Override
	public List<Review> findAllReviews(int videoId) {
		return reviewDao.selectAll(videoId);
	}

	@Override
	public int addReview(Review review) {
		return reviewDao.insertReview(review);
	}

	@Override
	public int modifyReview(Review review) {
		return reviewDao.updateReview(review);
	}

	@Override
	public int removeReview(int idx) {
		return reviewDao.deleteReview(idx);
	}

	@Override
	public Review findReview(int idx) {
		return reviewDao.selectReview(idx);
	}

}
