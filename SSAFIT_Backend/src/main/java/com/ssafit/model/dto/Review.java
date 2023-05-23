package com.ssafit.model.dto;

import java.time.LocalDateTime;

public class Review {
	
	private int idx;
	private int videoIdx;
	private String content;
	private int userIdx;
	private String writer;
	private int parent = 0;
	private LocalDateTime regDate;
	private LocalDateTime modDate;
	
	public Review() {
	
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getVideoIdx() {
		return videoIdx;
	}

	public void setVideoIdx(int videoIdx) {
		this.videoIdx = videoIdx;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUserIdx() {
		return userIdx;
	}

	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public LocalDateTime getModDate() {
		return modDate;
	}

	public void setModDate(LocalDateTime modDate) {
		this.modDate = modDate;
	}


}
