package com.ssafit.model.service;

import java.util.List;

import com.ssafit.exception.CustomException;
import com.ssafit.model.dto.Video;
import com.ssafit.model.dto.VideoSearchCondition;

public interface VideoService {

	public List<Video> findAllVideosByPart(List<String> parts);
	
	public List<Video> findAllVideos(VideoSearchCondition condition);
	
	public Video findVideo(int idx) throws CustomException;
	
	public Video findVideoByVideoId(String videoId);
	
	public int addVideo(Video video) throws CustomException;
	
	public int removeVideo(int idx) throws CustomException;
	
}
