package com.codingbamboo.miniproject.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.codingbamboo.miniproject.user.dto.UserDTO;

@Mapper
public interface IUserDAO {


	UserDTO getUser(String userId);

	int insertUser(UserDTO userInfo);

	UserDTO loginUser(UserDTO userInfo);

	int updateUser(UserDTO userInfo);

	int deleteUser(String userId);
}
