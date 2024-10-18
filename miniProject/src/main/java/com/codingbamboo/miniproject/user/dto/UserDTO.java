package com.codingbamboo.miniproject.user.dto;

import javax.validation.constraints.Pattern;

public class UserDTO {

    @Pattern(regexp = "^[a-z][a-zA-Z0-9]{5,}$", message = "¿Ã¹Ù¸£Áö ¾ÊÀº ¾ÆÀÌµğ ÀÔ´Ï´Ù. ¾ÆÀÌµğ´Â ¼ıÀÚ, ¿µ¹®À¸·Î¸¸ ÀÌ·ç¾îÁø 6±ÛÀÚ ÀÌ»óÀÌ¾î¾ß ÇÏ¸ç, ¿µ¹® ¼Ò¹®ÀÚ·Î ½ÃÀÛÇØ¾ß ÇÕ´Ï´Ù.")
    private String userId;

    @Pattern(regexp = "^(?=.*[a-z])[a-zA-Z0-9!@#$%^&*]{8,}$", message = "¿Ã¹Ù¸£Áö ¾ÊÀº ºñ¹Ğ¹øÈ£ ÀÔ´Ï´Ù. ¼ıÀÚ, ¿µ¹®, Æ¯¼ö¹®ÀÚ·Î¸¸ ÀÌ·ç¾îÁø 8±ÛÀÚ ÀÌ»óÀÌ¾î¾ß ÇÏ¸ç, ¿µ¹® ¼Ò¹®ÀÚ°¡ ¹İµå½Ã Æ÷ÇÔµÇ¾î¾ß ÇÕ´Ï´Ù.")
    private String userPw;

    @Pattern(regexp = "^[°¡-ÆRa-zA-Z]*$", message = "¿Ã¹Ù¸£Áö ¾ÊÀº ÀÌ¸§ÀÔ´Ï´Ù. ÀÌ¸§Àº ¼ıÀÚ³ª Æ¯¼ö¹®ÀÚ¸¦ Æ÷ÇÔÇÏÁö ¾Ê¾Æ¾ß ÇÕ´Ï´Ù.")
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
