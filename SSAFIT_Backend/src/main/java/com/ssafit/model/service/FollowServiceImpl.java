package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafit.model.dao.FollowDao;
import com.ssafit.model.dto.Follow;

public class FollowServiceImpl implements FollowService {

	@Autowired
	FollowDao followDao;
	
	@Override
	public List<Follow> getFollowList(String nickname) {
		return followDao.sellectAll(nickname);
	}

	@Override
	public Follow getFollow(Follow follow) {
		return followDao.selectFollow(follow);
	}

	@Override
	public int addFollow(Follow follow) {
		return followDao.insertFollow(follow);
	}

	@Override
	public int removeFollow(int idx) {
		return followDao.deleteFollow(idx);
	}

}
