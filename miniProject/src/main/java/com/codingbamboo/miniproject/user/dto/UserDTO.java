package com.codingbamboo.miniproject.user.dto;

import javax.validation.constraints.Pattern;

public class UserDTO {

    @Pattern(regexp = "^[a-z][a-zA-Z0-9]{5,}$", message = "올바르지 않은 아이디 입니다. 아이디는 숫자, 영문으로만 이루어진 6글자 이상이어야 하며, 영문 소문자로 시작해야 합니다.")
    private String userId;

    @Pattern(regexp = "^(?=.*[a-z])[a-zA-Z0-9!@#$%^&*]{8,}$", message = "올바르지 않은 비밀번호 입니다. 숫자, 영문, 특수문자로만 이루어진 8글자 이상이어야 하며, 영문 소문자가 반드시 포함되어야 합니다.")
    private String userPw;

    @Pattern(regexp = "^[가-힣a-zA-Z]*$", message = "올바르지 않은 이름입니다. 이름은 숫자나 특수문자를 포함하지 않아야 합니다.")
    private String userName;
	
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userPw=" + userPw + ", userName=" + userName + ", userEmail="
				+ userEmail + ", userIsmaster=" + userIsmaster + ", emailCheckCode=" + emailCheckCode + "]";
	}
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
    private String emailCheckCode; // 이메일 인증 코드
    
    public String getEmailCheckCode() { return emailCheckCode; }
    
    public void setEmailCheckCode(String emailCheckCode) { this.emailCheckCode = emailCheckCode; }
	
}
