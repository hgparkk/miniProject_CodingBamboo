package com.codingbamboo.miniproject.user.web;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codingbamboo.miniproject.user.dto.UserDTO;
import com.codingbamboo.miniproject.user.service.UserService;


@Controller
public class UserController {

	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	
	
	@Autowired
	UserService userService;
	
	//로그인뷰
	@RequestMapping("/loginView")
	public String loginView() {
		return "user/loginView";
	}
	
	//로그인
	@PostMapping("/loginDo")
	public String loginDo(UserDTO userInfo, HttpSession session, boolean rememberId, HttpServletResponse response, HttpServletRequest request, Model model) {
		UserDTO login = userService.loginUser(userInfo);
		
		
		boolean isMatch = passwordEncoder.matches(userInfo.getUserPw(), login.getUserPw());
		
		if(!isMatch) {
			model.addAttribute("errMsg", "비밀번호가 일치하지 않습니다.");
			return "user/loginView";
		}
		
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
		
		request.setAttribute("msg", "로그인 되었습니다");
		request.setAttribute("url", "/");
		
		return "alert";
	}
	
	
	
	
	//회원가입뷰
	@RequestMapping("/registView")
	public String registView() {
		return "user/registView";
	}
	
	

	@ResponseBody
	@PostMapping("/idDupCheck")
	public boolean idDupCheck(String inputId) {
	    UserDTO idCheck = new UserDTO();
	    idCheck.setUserId(inputId);
	    
	    UserDTO checked = userService.idDupCheck(idCheck);
	    
	    // null 체크 후 inputId와 조회된 ID를 비교
	    return (checked != null && checked.getUserId().equals(inputId));
	}

	
	//회원가입
	@PostMapping("/registDo")
	public String registDo(@Valid UserDTO user, BindingResult result, HttpServletRequest request) {
	    // 유효성 검증에서 오류가 발생한 경우
	    if(result.hasErrors()) {
	        for(ObjectError error : result.getAllErrors()) {
	            System.out.println(error.getDefaultMessage());
	        }
	        return "redirect:/registView";
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

	    // 회원 정보 출력 (디버깅용)
	    System.out.println("id = " + user.getUserId());
	    System.out.println("pw = " + user.getUserPw());
	    System.out.println("name = " + user.getUserName());
	    System.out.println("email = " + user.getUserEmail());
	    System.out.println("adminCheck = " + user.getUserIsmaster());

	    // 사용자 등록
	    userService.insertUser(user);

	    // 회원가입 성공 메시지 설정
	    request.setAttribute("msg", "회원가입이 완료되었습니다\\n로그인 창으로 이동합니다.");
	    request.setAttribute("url", "/loginView");

	    return "alert";
	}
	
	//로그아웃
	@RequestMapping("/logoutDo")
	public String logoutDo(HttpSession session, HttpServletRequest request) {
		session.invalidate();
		return "redirect:/";
	}
	
	//회원수정
	@PostMapping("/userEditDo")
	public String userEditDo(UserDTO user, HttpSession session, HttpServletRequest request) {
		userService.updateUser(user);
		
		
		UserDTO login = userService.getUser(user.getUserId());
		session.setAttribute("login", login);
		
		request.setAttribute("msg", "회원수정 되었습니다");
		request.setAttribute("url", "/");
		
		return "alert";
	}
	
	//회원수정뷰
	@RequestMapping("/userEditView")
	public String userEditView() {
		return "user/userEditView";
	}
	
	@RequestMapping("idpwFindView")
	public String idpwFindView(){
		return "user/idpwFindView";
	}
	
	//회원삭제
	@PostMapping("/userDelDo")
	public String userDelDo(HttpSession session, HttpServletRequest request) {
		
		
		UserDTO login = (UserDTO)session.getAttribute("login");
		userService.deleteUser(login.getUserId());
		session.invalidate();
		request.setAttribute("msg", "회원삭제 되었습니다");
		request.setAttribute("url", "/");
		
		return "alert";
		
	}
	
	//아이디 찾기
	@ResponseBody
	@PostMapping("/idFindDo")
	public String idFindDo(UserDTO user) {
		UserDTO result = userService.idFind(user);
		if(result != null) {
			return result.getUserId();
			
		}else {
			return "";
		}
	}
	
	
	
	

	
	


	
	
}
