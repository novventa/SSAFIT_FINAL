package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.exception.CustomException;
import com.ssafit.model.dao.ReviewDao;
import com.ssafit.model.dto.ErrorCode;
import com.ssafit.model.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao reviewDao;
	
	@Override
	public List<Review> findAllReviews(int videoIdx) {
		return reviewDao.selectAll(videoIdx);
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
	public Review findReview(int idx) throws CustomException {
		Review review = reviewDao.selectReview(idx);
		if(review == null) {
			throw new CustomException(ErrorCode.REVIEW_NOT_FOUND);
		}
		return review;
	}

}
