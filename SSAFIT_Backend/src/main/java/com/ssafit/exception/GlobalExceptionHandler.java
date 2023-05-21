package com.ssafit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafit.model.dto.ErrorResult;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResult> handleException(CustomException e) {
		ErrorResult er = new ErrorResult(e.getErrorCode());
		return new ResponseEntity<ErrorResult>(er, HttpStatus.valueOf(er.getStatus()));
	}
}
