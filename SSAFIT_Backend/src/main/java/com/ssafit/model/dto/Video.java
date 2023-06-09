package com.ssafit.model.dto;

public class Video {

	private int idx;
	private String videoId;
	private String part;
	private String title;
	private String channelName;
	private int viewCnt;
	private int likeCnt;
	
	public Video() {
	
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
	
}
