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
	public List<Review> getAllReviews(int videoId) {
		return reviewDao.selectAll(videoId);
	}

	@Override
	public int writeReview(Review review) {
		return reviewDao.insert(review);
	}

	@Override
	public int modifyReview(Review review) {
		return reviewDao.update(review);
	}

	@Override
	public int removeReview(int idx) {
		return reviewDao.delete(idx);
	}

	@Override
	public Review getReview(int idx) {
		return reviewDao.selectOne(idx);
	}

}
