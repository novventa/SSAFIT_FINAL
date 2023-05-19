package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.VideoDao;
import com.ssafit.model.dto.Video;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	VideoDao videoDao;
	
	@Override
	public List<Video> findAllVideos() {
		return videoDao.selectAll();
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

	@Override
	public int addLikeCnt(int idx) {
		return videoDao.updateLikeCnt(idx);
	}

}
