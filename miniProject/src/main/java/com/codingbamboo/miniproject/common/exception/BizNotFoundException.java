package com.codingbamboo.miniproject.common.exception;

// �츮�� �ٷ�� ������ ������ Ŭ������ ǥ���ϴ� ����
public class BizNotFoundException extends BizException{

	// �׳� �����ڸ� �߰�
	public BizNotFoundException() {
		super();
	}

	// throw new BizException("API_001", "�ش� �� ��ȣ�� �������� ����") ����
	// throw new BizNotFoundException("API_001", "�ش� �� ��ȣ�� �������� ����") ������
	public BizNotFoundException(String errCode, String message) {
		super(errCode, message);
	}
	
}
