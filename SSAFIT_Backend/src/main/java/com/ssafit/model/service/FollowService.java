package com.ssafit.model.service;

import java.util.List;

import com.ssafit.model.dto.Follow;

public interface FollowService {
	List<Follow> getFollowList(String nickname);
	
	Follow getFollow(Follow follow);
	
	int addFollow(Follow follow);
	
	int removeFollow(int idx);
}
