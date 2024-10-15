package com.codingbamboo.miniproject.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingbamboo.miniproject.user.dao.IUserDAO;
import com.codingbamboo.miniproject.user.dto.UserDTO;

@Service
public class UserService {

	@Autowired
	IUserDAO dao;
	
	//회원가입 메소드
	public int insertUser(UserDTO userInfo) {
		int result = dao.insertUser(userInfo);
		return result;
	}
	//로그인 메소드
	public UserDTO loginUser(UserDTO userInfo) {
		UserDTO result = dao.loginUser(userInfo);
		return result;
	}
	//회원수정 메소드
	public int updateUser(UserDTO userInfo) {
		int result = dao.updateUser(userInfo);
		return result;
	}
	//회원조회 메소드
	public UserDTO getUser(String userId) {
		UserDTO result = dao.getUser(userId);
		
		return result;
	}
	
	public int deleteUser(String userId) {
		int result = dao.deleteUser(userId);
		return result;
	}
	
}
