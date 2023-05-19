package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.LikeDao;
import com.ssafit.model.dto.Like;

@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	LikeDao dibsDao;
	
	@Override
	public List<Like> getLikeList(String nickname) {
		return dibsDao.selectAll(nickname);
	}
	
	@Override
	public Like getLike(Like like) {
		return dibsDao.selectLike(like);
	}

	@Override
	public int addLike(Like like) {
		return dibsDao.insertLike(like);
	}

	@Override
	public int removeLike(int idx) {
		return dibsDao.deleteLike(idx);
	}


}
