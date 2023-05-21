package com.ssafit.exception;

import com.ssafit.model.dto.ErrorCode;

public class CustomException extends Exception{
	
	private ErrorCode errorCode;

	public CustomException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
}