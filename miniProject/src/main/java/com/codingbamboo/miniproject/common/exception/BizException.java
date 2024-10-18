package com.codingbamboo.miniproject.common.exception;

// �츮�� ���� ����ϴ� ���� ���� �ֻ��� �θ� ��ü
// Exception ��ü�� ��ӹ޾ƾ� throw new BizException() ���·� ��� ����
public class BizException extends Exception{
	
	private String errCode;

	public BizException() {
		super();
	}

	// throw new BizException("API_001", "�ش� �� ��ȣ�� �������� ����")
	public BizException(String errCode, String message) {
		super(message);
		this.errCode = errCode;
	}

	public String getErrCode() {
		return errCode;
	}

}
