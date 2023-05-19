package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.LikeDao;
import com.ssafit.model.dto.Like;

@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	LikeDao likeDao;
	
	@Override
	public List<Like> findAllLikesByUser(int userIdx) {
		return likeDao.selectAllByUser(userIdx);
	}
	@Override
	public List<Like> findAllLikesByVideo(int videoIdx) {
		return likeDao.selectAllByVideo(videoIdx);
	}
	
	@Override
	public Like findLike(Like like) {
		return likeDao.selectLike(like);
	}

	@Override
	public int addLike(Like like) {
		return likeDao.insertLike(like);
	}

	@Override
	public int removeLike(int idx) {
		return likeDao.deleteLike(idx);
	}

}
