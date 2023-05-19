package com.ssafit.model.service;

import java.util.List;

import com.ssafit.model.dto.Like;

public interface LikeService {
	
	List<Like> findAllLikesByUser(int userIdx);

	List<Like> findAllLikesByVideo(int videoIdx);
	
	Like findLike(Like like);
	
	int addLike(Like like);

	int removeLike(int idx);
}
