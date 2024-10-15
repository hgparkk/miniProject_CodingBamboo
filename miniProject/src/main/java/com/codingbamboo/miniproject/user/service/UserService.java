package com.codingbamboo.miniproject.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingbamboo.miniproject.user.dao.IUserDAO;
import com.codingbamboo.miniproject.user.dto.UserDTO;

@Service
public class UserService {

	@Autowired
	IUserDAO dao;
	
	//ȸ������ �޼ҵ�
	public int insertUser(UserDTO userInfo) {
		int result = dao.insertUser(userInfo);
		return result;
	}
	//�α��� �޼ҵ�
	public UserDTO loginUser(UserDTO userInfo) {
		UserDTO result = dao.loginUser(userInfo);
		return result;
	}
	//ȸ������ �޼ҵ�
	public int updateUser(UserDTO userInfo) {
		int result = dao.updateUser(userInfo);
		return result;
	}
	//ȸ����ȸ �޼ҵ�
	public UserDTO getUser(String userId) {
		UserDTO result = dao.getUser(userId);
		
		return result;
	}
	
	public int deleteUser(String userId) {
		int result = dao.deleteUser(userId);
		return result;
	}
	
}
