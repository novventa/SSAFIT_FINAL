package com.ssafit.model.service;

import java.util.List;

import com.ssafit.model.dto.Likes;

public interface LikesService {
	
	List<Likes> findAllLikesByUser(int userIdx);

	List<Likes> findAllLikesByVideo(int videoIdx);
	
	Likes findLikes(Likes likes);
	
	int addLikes(Likes likes);

	int removeLikes(Likes likes);
}
