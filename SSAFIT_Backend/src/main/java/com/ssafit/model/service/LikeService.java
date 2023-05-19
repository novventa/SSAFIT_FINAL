package com.ssafit.model.service;

import java.util.List;

import com.ssafit.model.dto.Like;

public interface LikeService {
	List<Like> getLikeList(String nickname);

	Like getLike(Like like);
	
	int addLike(Like like);

	int removeLike(int idx);
}
