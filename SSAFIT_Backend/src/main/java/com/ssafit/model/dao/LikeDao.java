package com.ssafit.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafit.model.dto.Like;

@Repository
public interface LikeDao {
	List<Like> selectAllByUser(int userIdx);
	
	List<Like> selectAllByVideo(int videoIdx);	
	
	Like selectLike(Like like);
	
	int insertLike(Like like);
	
	int deleteLike(int idx);
}
