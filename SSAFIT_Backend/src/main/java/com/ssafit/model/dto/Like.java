package com.ssafit.model.dto;

public class Like {
	private int idx;
	private int videoIdx;
	private int userIdx;
	
	public Like() {
	}

	public Like(int videoIdx, int userIdx) {
		this.videoIdx = videoIdx;
		this.userIdx = userIdx;
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

	public int getUserIdx() {
		return userIdx;
	}

	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}

}
