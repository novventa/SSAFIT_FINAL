package com.ssafit.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafit.model.dto.Review;

@Repository 
public interface ReviewDao {

	public List<Review> selectAll(int videoId);
	public Review selectOne(int idx);
	public int insert(Review review);
	public int update(Review review);
	public int delete(int idx);
	
}
