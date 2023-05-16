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

	@GetMapping("/list")
	public ResponseEntity<?> list() {
		List<Video> list = videoService.getAllVideos();
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
	}

	@GetMapping("/{idx}")
	public ResponseEntity<?> select(@PathVariable int idx) {
		Video video = videoService.getVideo(idx);

		if (video == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		
		return new ResponseEntity<Video>(video, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Integer> add(Video video) {
		int result = videoService.addVideo(video);

		if (result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{idx}")
	public ResponseEntity<Integer> remove(@PathVariable int idx) {
		int result = videoService.removeVideo(idx);

		if (result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@PutMapping("/{idx}")
	public ResponseEntity<Integer> likeCnt(@PathVariable int idx) {
		int result = videoService.addLikeCnt(idx);

		if (result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

}
