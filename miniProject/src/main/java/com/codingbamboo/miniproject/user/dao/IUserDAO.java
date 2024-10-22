package com.codingbamboo.miniproject.user.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.User;

import com.codingbamboo.miniproject.user.dto.UserDTO;

@Mapper
public interface IUserDAO {


	UserDTO getUser(String userId);

	UserDTO loginUser(UserDTO userInfo);

	int updateUser(UserDTO userInfo);

	int deleteUser(String userId);

	UserDTO idDupCheck(UserDTO idCheck);

	UserDTO idFind(UserDTO user);

	UserDTO pwFind(UserDTO user);
	
	int pwReset(UserDTO user);

	int insertUser(UserDTO userInfo);

	User findByEmail(String email);

	UserDTO emailDupCheck(UserDTO emailCheck);

	int updateUserPw(UserDTO userInfo);
	
	
	
	
	
}


