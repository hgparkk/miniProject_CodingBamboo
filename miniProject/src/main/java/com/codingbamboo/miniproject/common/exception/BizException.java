package com.codingbamboo.miniproject.common.exception;

// 우리가 만들어서 사용하는 에러 중의 최상의 부모 객체
// Exception 객체를 상속받아야 throw new BizException() 형태로 사용 가능
public class BizException extends Exception{
	
	private String errCode;

	public BizException() {
		super();
	}

	// throw new BizException("API_001", "해당 글 번호가 존재하지 않음")
	public BizException(String errCode, String message) {
		super(message);
		this.errCode = errCode;
	}

	public String getErrCode() {
		return errCode;
	}

}
