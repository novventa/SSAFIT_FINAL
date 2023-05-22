package com.ssafit.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafit.model.dto.Review;

@Repository 
public interface ReviewDao {

	public List<Review> selectAll(int videoIdx);
	
	public List<Review> selectAllByUserIdx(int userIdx);
	
	public Review selectReview(int idx);
	
	public int insertReview(Review review);
	
	public int updateReview(Review review);
	
	public int deleteReview(int idx);
	
}
