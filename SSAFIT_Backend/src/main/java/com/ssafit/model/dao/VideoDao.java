package com.ssafit.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafit.model.dto.Video;
import com.ssafit.model.dto.VideoSearchCondition;

@Repository
public interface VideoDao {

	public List<Video> selectAll();

	public Video selectVideo(int idx);

	public int insertVideo(Video video);

	public int updateViewCnt(int idx);

	public int updateLikeCnt(int idx);

	public int deleteVideo(int idx);

	public List<Video> search(VideoSearchCondition condition);

}
