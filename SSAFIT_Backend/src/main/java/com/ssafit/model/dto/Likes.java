package com.ssafit.model.dto;

public class Likes {
	private int idx;
	private int videoIdx;
	private int userIdx;
	
	public Likes() {
	}

	public Likes(int videoIdx, int userIdx) {
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

	@Override
	public String toString() {
		return "Like [idx=" + idx + ", videoIdx=" + videoIdx + ", userIdx=" + userIdx + "]";
	}

}
