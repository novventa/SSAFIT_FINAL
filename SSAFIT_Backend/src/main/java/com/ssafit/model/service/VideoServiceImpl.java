package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.VideoDao;
import com.ssafit.model.dto.Video;
import com.ssafit.model.dto.VideoSearchCondition;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	VideoDao videoDao;
	
	@Override
	public List<Video> findAllVideos(VideoSearchCondition condition) {
		return videoDao.selectAll(condition);
	}
	
	@Override
	public List<Video> findAllVideosByPart(List<String> parts) {
		return videoDao.selectAllByPart(parts);
	}

	@Override
	public Video findVideo(int idx) {
		videoDao.updateViewCnt(idx);
		return videoDao.selectVideo(idx);
	}

	@Override
	public int addVideo(Video video) {
		return videoDao.insertVideo(video);
	}

	@Override
	public int removeVideo(int idx) {
		return videoDao.deleteVideo(idx);
	}

}
