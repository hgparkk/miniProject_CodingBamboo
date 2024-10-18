package com.codingbamboo.miniproject.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.codingbamboo.miniproject.util.XSSDestroy;

public class RequestWrapper extends HttpServletRequestWrapper{

	// �����ڷ� ���� Request ��ü�� ���� �Ķ���� ���鿡 ���� XSS �ı� 
	public RequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String[] getParameterValues(String name) {
		
		// ���͸��� ��ġ�� ���� ��û �Ķ���� ����
		String[] beforeParameters = super.getParameterValues(name);
		
		// ��û �Ķ���Ͱ� ������ (= null) �״�� null ����
		if(beforeParameters == null) {
			return null;
		}
		
		// ��û �Ķ���Ͱ� �ִٸ� ���� ���͸��� ������ �� ����
		for(int i = 0; i < beforeParameters.length; i++) {
			beforeParameters[i] = XSSDestroy.destroy(beforeParameters[i]);
		}
		
		return beforeParameters;
	}
	
	@Override
	public String getParameter(String name) {
		
		String beforeParam = super.getParameter(name);
		
		if(beforeParam == null) {
			return null;
		}
		return XSSDestroy.destroy(beforeParam);
	}
	
	@Override
	public String getHeader(String name) {
		String beforeHeader = super.getHeader(name);
		
		if(beforeHeader == null) {
			return null;
		}
		return XSSDestroy.destroy(beforeHeader);
	}
	
}
