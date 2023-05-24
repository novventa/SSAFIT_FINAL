package com.ssafit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.exception.CustomException;
import com.ssafit.model.dto.ErrorCode;
import com.ssafit.model.dto.Video;
import com.ssafit.model.dto.VideoSearchCondition;
import com.ssafit.model.service.VideoService;

@RestController
@RequestMapping("api-video")
public class VideoController {

	private static final String SUCCESS = "success";

	@Autowired
	VideoService videoService;

	@PostMapping("add")
	public ResponseEntity<?> videoAdd(Video video) throws CustomException {
		int result = videoService.addVideo(video);
		
		if (result == 0) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);	
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@GetMapping("list")
	public ResponseEntity<?> videoList(VideoSearchCondition condition) throws CustomException {
		List<Video> list = videoService.findAllVideos(condition);
		if(list == null) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		if (list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
	}

	@GetMapping("list-part")
	public ResponseEntity<?> videoListByPart(@RequestParam("parts") List<String> parts) throws CustomException {
		if(parts.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		List<Video> list = videoService.findAllVideosByPart(parts);
		if(list == null) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		if (list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
	}
	
	@GetMapping("details/{idx}")
	public ResponseEntity<?> videoDetails(@PathVariable int idx) throws CustomException {
		Video video = videoService.findVideo(idx);
		return new ResponseEntity<Video>(video, HttpStatus.OK);
	}

	@DeleteMapping("remove/{idx}")
	public ResponseEntity<?> videoRemove(@PathVariable int idx) throws CustomException {
		int result = videoService.removeVideo(idx);

		if (result == 0) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}


}
