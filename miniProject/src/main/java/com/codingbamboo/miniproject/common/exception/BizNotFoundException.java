package com.codingbamboo.miniproject.common.exception;

// 우리가 다루는 에러의 종류만 클래스명에 표기하는 느낌
public class BizNotFoundException extends BizException{

	// 그냥 생성자만 추가
	public BizNotFoundException() {
		super();
	}

	// throw new BizException("API_001", "해당 글 번호가 존재하지 않음") 보다
	// throw new BizNotFoundException("API_001", "해당 글 번호가 존재하지 않음") 적합함
	public BizNotFoundException(String errCode, String message) {
		super(errCode, message);
	}
	
}
