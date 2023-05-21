package com.ssafit.model.dto;

public class ErrorResult {
	private int status;
	private String msg;
	
	public ErrorResult(ErrorCode errorCode) {
		this.status = errorCode.getStatus();
		this.msg = errorCode.getMessage();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
