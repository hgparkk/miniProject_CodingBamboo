package com.codingbamboo.miniproject.user.web;


import java.net.http.HttpResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingbamboo.miniproject.user.dto.UserDTO;
import com.codingbamboo.miniproject.user.service.UserService;


@Controller
public class UserController {

	
	@Autowired
	UserService userService;
	
	//로그인뷰
	@RequestMapping("/loginView")
	public String loginView() {
		return "user/loginView";
	}
	
	//로그인
	@PostMapping("/loginDo")
	public String loginDo(UserDTO userInfo, HttpSession session, boolean rememberId, HttpServletResponse response) {
		UserDTO login = userService.loginUser(userInfo);
		
		session.setAttribute("login", login);
		
		if(rememberId) {
			//쿠키생성
			Cookie cookie = new Cookie("rememberId", userInfo.getUserId());
			//응답 객체에 쿠키를 담음
			response.addCookie(cookie);
		}else {
			Cookie cookie = new Cookie("rememberId", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		return "user/loginYn";
	}
	
	
	
	
	//회원가입뷰
	@RequestMapping("/registView")
	public String registView() {
		return "user/registView";
	}
	
	
	//회원가입
	@PostMapping("/registDo")
	public String registDo(HttpServletRequest request) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String ismasterStr = request.getParameter("adminCheck");
		
		int ismaster = 0;
		if (ismasterStr != null && !ismasterStr.isEmpty()) {
		    try {
		    	ismaster = Integer.parseInt(ismasterStr);
		    } catch (NumberFormatException e) {
		        e.printStackTrace();
		    }
		}
		
		
		System.out.println("id = " + id);
		System.out.println("pw = " + pw);
		System.out.println("name = " + name);
		System.out.println("email = " + email);
		System.out.println("adminCheck = " + ismaster);
		
		UserDTO user = new UserDTO(id, pw, name, email, ismaster);
		
		userService.insertUser(user);
		return "user/registNotice";
		
	}
	
	//로그아웃
	@RequestMapping("/logoutDo")
	public String logoutDo(HttpSession session, HttpServletRequest request) {
		session.invalidate();
		return "redirect:/";
	}
	
	//회원수정
	@PostMapping("/userEditDo")
	public String userEditDo(UserDTO user, HttpSession session) {
		userService.updateUser(user);
		
		
		UserDTO login = userService.getUser(user.getUserId());
		session.setAttribute("login", login);
		
		return "user/userEditNotice";
	}
	
	//회원수정뷰
	@RequestMapping("/userEditView")
	public String userEditView() {
		return "user/userEditView";
	}
	
	//회원삭제
	@PostMapping("/userDelDo")
	public String userDelDo(HttpSession session) {
		
		
		UserDTO login = (UserDTO)session.getAttribute("login");
		userService.deleteUser(login.getUserId());
		session.invalidate();
		
		return "user/userDelNotice";
	}
	
	//회원삭제 알림 뷰
	
	@RequestMapping("/userDelNotice")
	public String userDelNotice() {
		return "user/userDelNotice";
	}
	
	@RequestMapping("/loginYn")
	public String loginYn() {
		return "user/loginYn";
	}
	
	//회원수정 알림
	@RequestMapping("/userEditNotice")
	public String userEditNotice() {
		return "user/userEditNotice";
	}
	
	//회원가입 알림
	@RequestMapping("/registNotice")
	public String registNotice() {
		return "user/registNotice";
	}
	
	
	
}
