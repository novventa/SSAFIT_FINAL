package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.exception.CustomException;
import com.ssafit.model.dao.VideoDao;
import com.ssafit.model.dto.ErrorCode;
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
	public Video findVideo(int idx) throws CustomException {
		Video video = videoDao.selectVideo(idx);
		if(video == null) {
			throw new CustomException(ErrorCode.VIDEO_NOT_FOUND);
		}
		videoDao.updateViewCnt(idx);
		return video;
	}

	@Override
	public Video findVideoByVideoId(String videoId){
		return videoDao.selectVideoByVideoId(videoId);
	}
	
	@Override
	public int addVideo(Video video) throws CustomException {
		Video tmpVideo = videoDao.selectVideoByVideoId(video.getVideoId());
		if(tmpVideo != null) {
			throw new CustomException(ErrorCode.DUPLICATED_VIDEOID);
		}
		return videoDao.insertVideo(video);
	}

	@Override
	public int removeVideo(int idx) throws CustomException {
		findVideo(idx);
		return videoDao.deleteVideo(idx);
	}

}
