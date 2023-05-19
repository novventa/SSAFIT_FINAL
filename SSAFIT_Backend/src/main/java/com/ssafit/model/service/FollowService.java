package com.ssafit.model.service;

import java.util.List;

import com.ssafit.model.dto.Follow;

public interface FollowService {
	List<Follow> findAllFollows(int myIdx);
	
	Follow findFollow(Follow follow);
	
	int addFollow(Follow follow);
	
	int removeFollow(int idx);
}
