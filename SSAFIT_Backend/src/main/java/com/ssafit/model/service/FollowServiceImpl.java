package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.FollowDao;
import com.ssafit.model.dto.Follow;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	FollowDao followDao;
	
	@Override
	public List<Follow> findAllFollows(int myIdx) {
		return followDao.sellectAll(myIdx);
	}

	@Override
	public Follow findFollow(int idx) {
		return followDao.selectFollow(idx);
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
