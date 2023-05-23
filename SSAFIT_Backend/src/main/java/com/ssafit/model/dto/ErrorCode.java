package com.ssafit.model.dto;

public enum ErrorCode {

	INVALID_PARAMETER(400, "입력되지 않은 값이 있습니다."),
	WRONG_PASSWORD(400, "비밀번호가 일치하지 않습니다."),
	FAIL_CERTIFICATION(400, "유저 확인에 실패하였습니다."),
	
	USER_NOT_FOUND(404, "존재하지 않는 계정입니다."),
    REVIEW_NOT_FOUND(404, "존재하지 않는 리뷰입니다."),
    VIDEO_NOT_FOUND(404, "존재하지 않는 영상입니다."),

    //409 CONFLICT 중복된 리소스
    DUPLICATED_ID(409, "이미 사용중인 아이디입니다."),
    DUPLICATED_EMAIL(409, "이미 사용중인 이메일입니다."),
    DUPLICATED_NICKNAME(409, "이미 사용중인 닉네임입니다."),
    DUPLICATED_VIDEOID(409, "중복된 VIDEOID가 존재합니다."),
    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500, "에러가 발생하였습니다.");
	
	private ErrorCode(int status, String message) {
		this.status = status;
		this.message = message;
	}

	private final int status;
	private final String message;

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}