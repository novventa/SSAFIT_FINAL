package com.ssafit.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafit.model.dto.Video;
import com.ssafit.model.dto.VideoSearchCondition;

@Repository
public interface VideoDao {
	
	public List<Video> selectAll();
	public Video selectOne(int idx);
	public int insert(Video video);
	public int updateViewCnt(int idx);
	public int updateLikeCnt(int idx);
	public int delete(int idx);
	public List<Video> search(VideoSearchCondition condition);
	
}
