package com.codingbamboo.miniproject.user.web;

import java.security.SecureRandom;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingbamboo.miniproject.user.dto.UserDTO;
import com.codingbamboo.miniproject.user.service.UserService;

@Controller
public class UserController {

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	UserService userService;

	// �α��� â
	@RequestMapping("/loginView")
	public String loginView(HttpServletRequest request, Model model,
			@RequestParam(value = "fromUrl", required = false) String fromUrl) {
		if (fromUrl == null || fromUrl.isEmpty()) {
			fromUrl = request.getHeader("Referer");
		}
		model.addAttribute("fromUrl", fromUrl);
		return "user/loginView";
	}

	// �α���
	@PostMapping("/loginDo")
	public String loginDo(String fromUrl, UserDTO userInfo, HttpSession session, boolean rememberId,
			HttpServletResponse response, HttpServletRequest request, Model model, RedirectAttributes attr) {
		UserDTO login = userService.loginUser(userInfo);

		boolean isMatch = passwordEncoder.matches(userInfo.getUserPw(), login.getUserPw());
		if (!isMatch) {
			model.addAttribute("errMsg", "���̵� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			attr.addAttribute("fromUrl", fromUrl);
			return "user/loginView";
		}

		// �α��� ���� ��
		session.setAttribute("login", login);

		// ���̵� ����ϱ� ó��
		if (rememberId) {
			Cookie cookie = new Cookie("rememberId", userInfo.getUserId());
			response.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie("rememberId", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}

		if (fromUrl.contains("/boardView") || fromUrl.contains("/boardDetailView")
				|| fromUrl.contains("/materialCalculation") || fromUrl.contains("/electricCalculation")) {
			return "redirect:" + fromUrl;
		} else {
			return "redirect:/";
		}
	}

	// ȸ������ â
	@RequestMapping("/registView")
	public String registView() {
		return "user/registView";
	}

	// ���̵� �ߺ�üũ
	@ResponseBody
	@PostMapping("/idDupCheck")
	public boolean idDupCheck(String inputId) {
		UserDTO idCheck = new UserDTO();
		idCheck.setUserId(inputId);

		UserDTO checked = userService.idDupCheck(idCheck);

		// null üũ �� inputId�� ��ȸ�� ID�� ��
		return (checked != null && checked.getUserId().equals(inputId));
	}

	// ȸ������
	@PostMapping("/registDo")
	public String registDo(@Valid UserDTO user, BindingResult result, HttpServletRequest request, HttpSession session) {
		UserDTO emailCheck = new UserDTO();
		emailCheck.setUserEmail(user.getUserEmail());

		// ��ȿ�� �������� ������ �߻��� ���
		if (result.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder();
			for (ObjectError error : result.getAllErrors()) {
				errorMessage.append(error.getDefaultMessage()).append("\\n");
			}

			// ���� �޽����� request�� �߰�
			request.setAttribute("msg", errorMessage.toString());
			request.setAttribute("url", "/registView");

			return "alert"; // alert �������� �̵�
		} else if (userService.emailDupCheck(emailCheck) != null) {
			request.setAttribute("msg", "�̹� ��ϵ� �̸��� �Դϴ�. �ٸ� �̸����� �Է��Ͽ� �ּ���");
			request.setAttribute("url", "/registView");
			return "alert";
		}

		// �̸��� ���� �ڵ� Ȯ��
		String inputCode = user.getEmailCheckCode(); // ����ڰ� �Է��� ���� �ڵ�
		String expectedCode = (String) session.getAttribute("emailCheckCode"); // ���ǿ��� ������ ���� �ڵ�

		if (expectedCode == null || !inputCode.equals(expectedCode)) {
			// ���� �ڵ尡 ��ġ���� ���� ��� ���� ó��
			request.setAttribute("msg", "���� �ڵ尡 ��ġ���� �ʽ��ϴ�. �ٽ� �õ����ּ���.");
			request.setAttribute("url", "/registView");
			return "alert"; // alert �������� �̵�
		}

		// ��й�ȣ ��ȣȭ
		String encodePw = passwordEncoder.encode(user.getUserPw());
		user.setUserPw(encodePw);

		// ���������� ���� Ȯ�� �� ó��
		String ismasterStr = request.getParameter("adminCheck");
		int ismaster = 0;
		if (ismasterStr != null && !ismasterStr.isEmpty()) {
			try {
				ismaster = Integer.parseInt(ismasterStr);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		user.setUserIsmaster(ismaster);

		// ����� ���
		userService.insertUser(user);

		// ȸ������ ���� �޽��� ����
		request.setAttribute("msg", "ȸ�������� �Ϸ�Ǿ����ϴ�\\n�α��� â���� �̵��մϴ�.");
		request.setAttribute("url", "/loginView");

		// ���ǿ��� ���� �ڵ� ���� (���� ����)
		session.removeAttribute("emailCheckCode");

		return "alert";
	}

	// �α׾ƿ�
	@RequestMapping("/logoutDo")
	public String logoutDo(HttpSession session, HttpServletRequest request) {
		String fromUrl = request.getHeader("Referer");
		session.invalidate();
		if (fromUrl.contains("/boardView") || fromUrl.contains("/boardDetailView")
				|| fromUrl.contains("/materialCalculation") || fromUrl.contains("/electricCalculation")) {
			return "redirect:" + fromUrl;
		} else {
			return "redirect:/";
		}
	}

	// ȸ������ â
	@RequestMapping("/userEditView")
	public String userEditView() {
		return "user/userEditView";
	}

	// ȸ������
	@PostMapping("/userEditDo")
	public String userEditDo(UserDTO user, HttpSession session, HttpServletRequest request) {
		String encodePw = passwordEncoder.encode(user.getUserPw());
		user.setUserPw(encodePw);

		userService.updateUser(user);

		UserDTO login = userService.getUser(user.getUserId());
		session.setAttribute("login", login);

		request.setAttribute("msg", "ȸ������ �Ǿ����ϴ�");
		request.setAttribute("url", "/");

		return "alert";
	}

	// ���̵� ��й�ȣ ã�� â
	@RequestMapping("/idpwFindView")
	public String idpwFindView() {
		return "user/idpwFindView";
	}

	// ȸ��Ż��
	@PostMapping("/userDelDo")
	public String userDelDo(HttpSession session, HttpServletRequest request) {

		UserDTO login = (UserDTO) session.getAttribute("login");
		userService.deleteUser(login.getUserId());
		session.invalidate();
		request.setAttribute("msg", "ȸ��Ż�� �Ϸ�Ǿ����ϴ�");
		request.setAttribute("url", "/");

		return "alert";
	}

	// ���̵� ã��
	@ResponseBody
	@PostMapping("/idFindDo")
	public String idFindDo(UserDTO user) {
		UserDTO result = userService.idFind(user);

		if (result != null) {
			// ���� ID
			String v_userId = result.getUserId();

			// ù 4���ڸ� ����
			String cutedUserId = v_userId.substring(0, 4);

			// ������ ���ڼ��� ���ؼ� �ش� ���̸�ŭ '*' �߰�
			StringBuilder masked = new StringBuilder(cutedUserId);
			for (int i = 4; i < v_userId.length(); i++) {
				masked.append("*");
			}

			// ����ŷ�� ID ��ȯ
			return masked.toString();
		} else {
			return ""; // ID�� ã�� ������ ��� �� ���ڿ� ��ȯ
		}
	}

	// PWreset ȭ������ �̵�
	@PostMapping("/pwResetView")
	public String pwResetView(String resetId, Model model) {
		model.addAttribute("resetId", resetId);
		return "user/loginView";
	}

	// PW �ʱ�ȭ
	@PostMapping("/pwResetDo")
	public String pwResetDo(@Valid UserDTO user, BindingResult result, HttpServletRequest request) {
		if (result.hasErrors()) {
			String errMsg = "";
			for (ObjectError error : result.getAllErrors()) {
				errMsg += error.getDefaultMessage();
				errMsg += "\\n\\n";
			}
			errMsg += "�ش� �۾��� �ٽ� �õ��Ͽ� �ּ���.";
			request.setAttribute("msg", errMsg);
			request.setAttribute("url", "/idpwFindView");
			return "alert";
		} else {
			String encodePw = passwordEncoder.encode(user.getUserPw());
			user.setUserPw(encodePw);
			userService.pwReset(user);
			request.setAttribute("msg", "�����Ͻ� ��й�ȣ�� ��й�ȣ�� �ʱ�ȭ �Ǿ����ϴ�.");
			request.setAttribute("url", "/loginView");
			return "alert";
		}
	}

	// PW ã��
	@ResponseBody
	@PostMapping("/pwFindDo")
	public String pwFindDo(UserDTO user, String userEmail, HttpSession session) {
		int length = 8;
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder code = new StringBuilder();
		SecureRandom random = new SecureRandom();

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(characters.length());
			code.append(characters.charAt(randomIndex));
		}

		String changedPw = code.toString(); // ������ ���� ���
		System.out.println(userEmail);

		if (user == null) {
			return "fail"; // �̸����� �������� ���� ��� ���� ��ȯ
		}

		// ��й�ȣ ��ȣȭ
		String encodedPw = passwordEncoder.encode(changedPw);
		user.setUserPw(encodedPw);

		// ��й�ȣ ������Ʈ
		userService.updateUserPw(user);

		userEmail = userEmail.replace("&#64;", "@");
		System.out.println(userEmail);

		// ���ǿ� ���� �ڵ� ����
		session.setAttribute("changedPw", changedPw);

		Email email = new SimpleEmail();
		email.setHostName("smtp.naver.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("jm86245@naver.com", "GRKNNYCV9QDG"));
		email.setSSL(true);
		try {
			email.setFrom("jm86245@naver.com", "CodingBamboo");
			email.setSubject("��й�ȣ�� ����Ǿ����ϴ�");
			email.setMsg("����� ��й�ȣ : " + changedPw + "�Դϴ�. �ش� ��й�ȣ�� �α����� ȸ�������� ���� ��й�ȣ�� �ٲ��ּ���."); // �̸��� ���뿡 ���� �ڵ带 ����
			email.addTo(userEmail, "");
			email.send();
			return "success";
		} catch (EmailException e) {
			e.printStackTrace();
		}
		return "fail";
	}

	// �������� ����
	@ResponseBody
	@PostMapping("/sendEmail")
	public String sendEmail(String inputEmail, HttpSession session) {
		// ���� �ڵ� ����
		int length = 6; // ������ �ڵ��� ����
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder code = new StringBuilder();
		SecureRandom random = new SecureRandom();

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(characters.length());
			code.append(characters.charAt(randomIndex));
		}

		String emailCheckCode = code.toString(); // ������ ���� �ڵ�
		System.out.println(inputEmail);

		inputEmail = inputEmail.replace("&#64;", "@");
		System.out.println(inputEmail);

		// ���ǿ� ���� �ڵ� ����
		session.setAttribute("emailCheckCode", emailCheckCode);

		Email email = new SimpleEmail();
		email.setHostName("smtp.naver.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("jm86245@naver.com", "GRKNNYCV9QDG"));
		email.setSSL(true);
		try {
			email.setFrom("jm86245@naver.com", "CodingBamboo");
			email.setSubject("��������");
			email.setMsg("���� �ڵ� : " + emailCheckCode); // �̸��� ���뿡 ���� �ڵ带 ����
			email.addTo(inputEmail, "");
			email.send();
			return "success";
		} catch (EmailException e) {
			e.printStackTrace();
		}
		return "fail";
	}
}