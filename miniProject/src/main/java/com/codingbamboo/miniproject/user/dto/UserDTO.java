package com.codingbamboo.miniproject.user.dto;

public class UserDTO {

	
	private String userId;
	private String userPw;
	private String userName;
	private String userEmail;
	private int userIsmaster;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getUserIsmaster() {
		return userIsmaster;
	}
	public void setUserIsmaster(int userIsmaster) {
		this.userIsmaster = userIsmaster;
	}
	public UserDTO(String userId, String userPw, String userName, String userEmail, int userIsmaster) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userIsmaster = userIsmaster;
	}
	public UserDTO() {
		super();
	}
	
}
