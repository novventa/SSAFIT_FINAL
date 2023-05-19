package com.ssafit.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafit.model.dto.Likes;

@Repository
public interface LikesDao {
	List<Likes> selectAllByUser(int userIdx);
	
	List<Likes> selectAllByVideo(int videoIdx);	
	
	Likes selectLikes(Likes likes);
	
	int insertLikes(Likes likes);
	
	int deleteLikes(int idx);
}
