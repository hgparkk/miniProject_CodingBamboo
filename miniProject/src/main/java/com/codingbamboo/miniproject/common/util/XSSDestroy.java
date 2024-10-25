package com.codingbamboo.miniproject.common.util;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

public class XSSDestroy {
	
	// ������� �Է°����� ����� �±׿� �Ӽ��� ����
	private static final PolicyFactory policy = new HtmlPolicyBuilder()
			.allowElements("p", "b", "i", "u", "string", "em", "a", "img","span")
			.allowAttributes("href", "target", "class").onElements("a")
			.allowAttributes("src", "alt", "style", "width", "height", "class").onElements("img")
			.allowAttributes("style").onElements("span")
			.allowStandardUrlProtocols()
			.toFactory();
	
	// �Ķ���Ϳ� ���ڿ��� ������ �� ������ ������ �±� �� �Ӽ��� �ı��� ����� ����
	public static String destroy(String inputText) {
		return policy.sanitize(inputText);
	}

}
