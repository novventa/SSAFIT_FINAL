package com.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.model.dto.Video;
import com.ssafit.model.service.VideoService;

@RestController
@RequestMapping("api-video")
public class VideoController {

	@Autowired
	VideoService videoService;

	@PostMapping("add")
	public ResponseEntity<Integer> videoAdd(Video video) {
		int result = videoService.addVideo(video);
		
		if (result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping("list")
	public ResponseEntity<?> videoList() {
		List<Video> list = videoService.findAllVideos();
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
	}

	@GetMapping("details/{idx}")
	public ResponseEntity<?> videoDetails(@PathVariable int idx) {
		Video video = videoService.findVideo(idx);

		if (video == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		
		return new ResponseEntity<Video>(video, HttpStatus.OK);
	}

	@PutMapping("modify-like/{idx}")
	public ResponseEntity<Integer> likeCntModify(@PathVariable int idx) {
		int result = videoService.modifyLikeCnt(idx);
		
		if (result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@DeleteMapping("remove/{idx}")
	public ResponseEntity<Integer> videoRemove(@PathVariable int idx) {
		int result = videoService.removeVideo(idx);

		if (result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}


}
