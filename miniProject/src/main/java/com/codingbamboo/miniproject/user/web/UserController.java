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
	
	//�α��κ�
	@RequestMapping("/loginView")
	public String loginView() {
		return "user/loginView";
	}
	
	//�α���
	@PostMapping("/loginDo")
	public String loginDo(UserDTO userInfo, HttpSession session, boolean rememberId, HttpServletResponse response, HttpServletRequest request, Model model) {
		UserDTO login = userService.loginUser(userInfo);
		
		
		boolean isMatch = passwordEncoder.matches(userInfo.getUserPw(), login.getUserPw());
		
		if(!isMatch) {
			model.addAttribute("errMsg", "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			return "user/loginView";
		}
		
		session.setAttribute("login", login);
		
		if(rememberId) {
			//��Ű����
			Cookie cookie = new Cookie("rememberId", userInfo.getUserId());
			//���� ��ü�� ��Ű�� ����
			response.addCookie(cookie);
		}else {
			Cookie cookie = new Cookie("rememberId", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		request.setAttribute("msg", "�α��� �Ǿ����ϴ�");
		request.setAttribute("url", "/");
		
		return "alert";
	}
	
	
	
	
	//ȸ�����Ժ�
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
	    
	    // null üũ �� inputId�� ��ȸ�� ID�� ��
	    return (checked != null && checked.getUserId().equals(inputId));
	}

	
	//ȸ������
	@PostMapping("/registDo")
	public String registDo(@Valid UserDTO user, BindingResult result, HttpServletRequest request) {
	    // ��ȿ�� �������� ������ �߻��� ���
	    if(result.hasErrors()) {
	        for(ObjectError error : result.getAllErrors()) {
	            System.out.println(error.getDefaultMessage());
	        }
	        return "redirect:/registView";
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

	    // ȸ�� ���� ��� (������)
	    System.out.println("id = " + user.getUserId());
	    System.out.println("pw = " + user.getUserPw());
	    System.out.println("name = " + user.getUserName());
	    System.out.println("email = " + user.getUserEmail());
	    System.out.println("adminCheck = " + user.getUserIsmaster());

	    // ����� ���
	    userService.insertUser(user);

	    // ȸ������ ���� �޽��� ����
	    request.setAttribute("msg", "ȸ�������� �Ϸ�Ǿ����ϴ�\\n�α��� â���� �̵��մϴ�.");
	    request.setAttribute("url", "/loginView");

	    return "alert";
	}
	
	//�α׾ƿ�
	@RequestMapping("/logoutDo")
	public String logoutDo(HttpSession session, HttpServletRequest request) {
		session.invalidate();
		return "redirect:/";
	}
	
	//ȸ������
	@PostMapping("/userEditDo")
	public String userEditDo(UserDTO user, HttpSession session, HttpServletRequest request) {
		userService.updateUser(user);
		
		
		UserDTO login = userService.getUser(user.getUserId());
		session.setAttribute("login", login);
		
		request.setAttribute("msg", "ȸ������ �Ǿ����ϴ�");
		request.setAttribute("url", "/");
		
		return "alert";
	}
	
	//ȸ��������
	@RequestMapping("/userEditView")
	public String userEditView() {
		return "user/userEditView";
	}
	
	@RequestMapping("idpwFindView")
	public String idpwFindView(){
		return "user/idpwFindView";
	}
	
	//ȸ������
	@PostMapping("/userDelDo")
	public String userDelDo(HttpSession session, HttpServletRequest request) {
		
		
		UserDTO login = (UserDTO)session.getAttribute("login");
		userService.deleteUser(login.getUserId());
		session.invalidate();
		request.setAttribute("msg", "ȸ������ �Ǿ����ϴ�");
		request.setAttribute("url", "/");
		
		return "alert";
		
	}
	
	//���̵� ã��
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
