package com.ssafit.model.dao;

import java.util.List;

import com.ssafit.model.dto.Follow;

public interface FollowDao {
	List<Follow> sellectAll(int myIdx);
	
	Follow selectFollow(Follow follow);
	
	int insertFollow(Follow follow);
	
	int deleteFollow(int idx);
}