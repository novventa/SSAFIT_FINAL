package com.ssafit.model.dto;

public class Follow {
	private int idx;
	private int myIdx;
	private int yourIdx;
	
	public Follow() {
	}

	public Follow(int myIdx, int yourIdx) {
		this.myIdx = myIdx;
		this.yourIdx = yourIdx;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getMyIdx() {
		return myIdx;
	}

	public void setMyIdx(int myIdx) {
		this.myIdx = myIdx;
	}

	public int getYourIdx() {
		return yourIdx;
	}

	public void setYourIdx(int yourIdx) {
		this.yourIdx = yourIdx;
	}
	
}
