package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.LikesDao;
import com.ssafit.model.dto.Likes;

@Service
public class LikesServiceImpl implements LikesService {

	@Autowired
	LikesDao likesDao;
	
	@Override
	public List<Likes> findAllLikesByUser(int userIdx) {
		return likesDao.selectAllByUser(userIdx);
	}
	@Override
	public List<Likes> findAllLikesByVideo(int videoIdx) {
		return likesDao.selectAllByVideo(videoIdx);
	}
	
	@Override
	public Likes findLikes(int idx) {
		return likesDao.selectLikes(idx);
	}

	@Override
	public int addLikes(Likes likes) {
		return likesDao.insertLikes(likes);
	}

	@Override
	public int removeLikes(int idx) {
		return likesDao.deleteLikes(idx);
	}

}
