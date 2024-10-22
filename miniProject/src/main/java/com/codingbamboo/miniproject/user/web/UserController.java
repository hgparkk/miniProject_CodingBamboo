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

	// 로그인 창
	@RequestMapping("/loginView")
	public String loginView(HttpServletRequest request, Model model,
			@RequestParam(value = "fromUrl", required = false) String fromUrl) {
		if (fromUrl == null || fromUrl.isEmpty()) {
			fromUrl = request.getHeader("Referer");
		}
		model.addAttribute("fromUrl", fromUrl);
		return "user/loginView";
	}

	// 로그인
	@PostMapping("/loginDo")
	public String loginDo(String fromUrl, UserDTO userInfo, HttpSession session, boolean rememberId,
			HttpServletResponse response, HttpServletRequest request, Model model, RedirectAttributes attr) {
		UserDTO login = userService.loginUser(userInfo);

		boolean isMatch = passwordEncoder.matches(userInfo.getUserPw(), login.getUserPw());
		if (!isMatch) {
			model.addAttribute("errMsg", "아이디나 비밀번호가 일치하지 않습니다.");
			attr.addAttribute("fromUrl", fromUrl);
			return "user/loginView";
		}

		// 로그인 성공 시
		session.setAttribute("login", login);

		// 아이디 기억하기 처리
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

	// 회원가입 창
	@RequestMapping("/registView")
	public String registView() {
		return "user/registView";
	}

	// 아이디 중복체크
	@ResponseBody
	@PostMapping("/idDupCheck")
	public boolean idDupCheck(String inputId) {
		UserDTO idCheck = new UserDTO();
		idCheck.setUserId(inputId);

		UserDTO checked = userService.idDupCheck(idCheck);

		// null 체크 후 inputId와 조회된 ID를 비교
		return (checked != null && checked.getUserId().equals(inputId));
	}

	// 회원가입
	@PostMapping("/registDo")
	public String registDo(@Valid UserDTO user, BindingResult result, HttpServletRequest request, HttpSession session) {
		UserDTO emailCheck = new UserDTO();
		emailCheck.setUserEmail(user.getUserEmail());

		// 유효성 검증에서 오류가 발생한 경우
		if (result.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder();
			for (ObjectError error : result.getAllErrors()) {
				errorMessage.append(error.getDefaultMessage()).append("\\n");
			}

			// 에러 메시지를 request에 추가
			request.setAttribute("msg", errorMessage.toString());
			request.setAttribute("url", "/registView");

			return "alert"; // alert 페이지로 이동
		} else if (userService.emailDupCheck(emailCheck) != null) {
			request.setAttribute("msg", "이미 등록된 이메일 입니다. 다른 이메일을 입력하여 주세요");
			request.setAttribute("url", "/registView");
			return "alert";
		}

		// 이메일 인증 코드 확인
		String inputCode = user.getEmailCheckCode(); // 사용자가 입력한 인증 코드
		String expectedCode = (String) session.getAttribute("emailCheckCode"); // 세션에서 가져온 인증 코드

		if (expectedCode == null || !inputCode.equals(expectedCode)) {
			// 인증 코드가 일치하지 않을 경우 에러 처리
			request.setAttribute("msg", "인증 코드가 일치하지 않습니다. 다시 시도해주세요.");
			request.setAttribute("url", "/registView");
			return "alert"; // alert 페이지로 이동
		}

		// 비밀번호 암호화
		String encodePw = passwordEncoder.encode(user.getUserPw());
		user.setUserPw(encodePw);

		// 관리자인지 여부 확인 및 처리
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

		// 사용자 등록
		userService.insertUser(user);

		// 회원가입 성공 메시지 설정
		request.setAttribute("msg", "회원가입이 완료되었습니다\\n로그인 창으로 이동합니다.");
		request.setAttribute("url", "/loginView");

		// 세션에서 인증 코드 제거 (선택 사항)
		session.removeAttribute("emailCheckCode");

		return "alert";
	}

	// 로그아웃
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

	// 회원수정 창
	@RequestMapping("/userEditView")
	public String userEditView() {
		return "user/userEditView";
	}

	// 회원수정
	@PostMapping("/userEditDo")
	public String userEditDo(UserDTO user, HttpSession session, HttpServletRequest request) {
		String encodePw = passwordEncoder.encode(user.getUserPw());
		user.setUserPw(encodePw);

		userService.updateUser(user);

		UserDTO login = userService.getUser(user.getUserId());
		session.setAttribute("login", login);

		request.setAttribute("msg", "회원수정 되었습니다");
		request.setAttribute("url", "/");

		return "alert";
	}

	// 아이디 비밀번호 찾기 창
	@RequestMapping("/idpwFindView")
	public String idpwFindView() {
		return "user/idpwFindView";
	}

	// 회원탈퇴
	@PostMapping("/userDelDo")
	public String userDelDo(HttpSession session, HttpServletRequest request) {

		UserDTO login = (UserDTO) session.getAttribute("login");
		userService.deleteUser(login.getUserId());
		session.invalidate();
		request.setAttribute("msg", "회원탈퇴가 완료되었습니다");
		request.setAttribute("url", "/");

		return "alert";
	}

	// 아이디 찾기
	@ResponseBody
	@PostMapping("/idFindDo")
	public String idFindDo(UserDTO user) {
		UserDTO result = userService.idFind(user);

		if (result != null) {
			// 유저 ID
			String v_userId = result.getUserId();

			// 첫 4글자만 추출
			String cutedUserId = v_userId.substring(0, 4);

			// 나머지 글자수를 구해서 해당 길이만큼 '*' 추가
			StringBuilder masked = new StringBuilder(cutedUserId);
			for (int i = 4; i < v_userId.length(); i++) {
				masked.append("*");
			}

			// 마스킹된 ID 반환
			return masked.toString();
		} else {
			return ""; // ID를 찾지 못했을 경우 빈 문자열 반환
		}
	}

	// PWreset 화면으로 이동
	@PostMapping("/pwResetView")
	public String pwResetView(String resetId, Model model) {
		model.addAttribute("resetId", resetId);
		return "user/loginView";
	}

	// PW 초기화
	@PostMapping("/pwResetDo")
	public String pwResetDo(@Valid UserDTO user, BindingResult result, HttpServletRequest request) {
		if (result.hasErrors()) {
			String errMsg = "";
			for (ObjectError error : result.getAllErrors()) {
				errMsg += error.getDefaultMessage();
				errMsg += "\\n\\n";
			}
			errMsg += "해당 작업을 다시 시도하여 주세요.";
			request.setAttribute("msg", errMsg);
			request.setAttribute("url", "/idpwFindView");
			return "alert";
		} else {
			String encodePw = passwordEncoder.encode(user.getUserPw());
			user.setUserPw(encodePw);
			userService.pwReset(user);
			request.setAttribute("msg", "설정하신 비밀번호로 비밀번호가 초기화 되었습니다.");
			request.setAttribute("url", "/loginView");
			return "alert";
		}
	}

	// PW 찾기
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

		String changedPw = code.toString(); // 생성된 랜덤 비번
		System.out.println(userEmail);

		if (user == null) {
			return "fail"; // 이메일이 존재하지 않을 경우 실패 반환
		}

		// 비밀번호 암호화
		String encodedPw = passwordEncoder.encode(changedPw);
		user.setUserPw(encodedPw);

		// 비밀번호 업데이트
		userService.updateUserPw(user);

		userEmail = userEmail.replace("&#64;", "@");
		System.out.println(userEmail);

		// 세션에 인증 코드 저장
		session.setAttribute("changedPw", changedPw);

		Email email = new SimpleEmail();
		email.setHostName("smtp.naver.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("jm86245@naver.com", "GRKNNYCV9QDG"));
		email.setSSL(true);
		try {
			email.setFrom("jm86245@naver.com", "CodingBamboo");
			email.setSubject("비밀번호가 변경되었습니다");
			email.setMsg("변경된 비밀번호 : " + changedPw + "입니다. 해당 비밀번호로 로그인후 회원수정을 통해 비밀번호를 바꿔주세요."); // 이메일 내용에 인증 코드를 포함
			email.addTo(userEmail, "");
			email.send();
			return "success";
		} catch (EmailException e) {
			e.printStackTrace();
		}
		return "fail";
	}

	// 인증메일 전송
	@ResponseBody
	@PostMapping("/sendEmail")
	public String sendEmail(String inputEmail, HttpSession session) {
		// 랜덤 코드 생성
		int length = 6; // 생성할 코드의 길이
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder code = new StringBuilder();
		SecureRandom random = new SecureRandom();

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(characters.length());
			code.append(characters.charAt(randomIndex));
		}

		String emailCheckCode = code.toString(); // 생성된 랜덤 코드
		System.out.println(inputEmail);

		inputEmail = inputEmail.replace("&#64;", "@");
		System.out.println(inputEmail);

		// 세션에 인증 코드 저장
		session.setAttribute("emailCheckCode", emailCheckCode);

		Email email = new SimpleEmail();
		email.setHostName("smtp.naver.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("jm86245@naver.com", "GRKNNYCV9QDG"));
		email.setSSL(true);
		try {
			email.setFrom("jm86245@naver.com", "CodingBamboo");
			email.setSubject("인증메일");
			email.setMsg("인증 코드 : " + emailCheckCode); // 이메일 내용에 인증 코드를 포함
			email.addTo(inputEmail, "");
			email.send();
			return "success";
		} catch (EmailException e) {
			e.printStackTrace();
		}
		return "fail";
	}
}