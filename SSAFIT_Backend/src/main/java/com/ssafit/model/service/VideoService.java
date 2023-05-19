package com.ssafit.model.service;

import java.util.List;

import com.ssafit.model.dto.Video;

public interface VideoService {

	public List<Video> findAllVideos();
	public Video findVideo(int idx);
	public int addVideo(Video video);
	public int removeVideo(int idx);
	public int addLikeCnt(int idx);
	
}
