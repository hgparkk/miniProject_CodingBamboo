package com.codingbamboo.miniproject.board.dto;

public class BoardDTO {
	private int quNo;
	private String userId;
	private String userName;
	private String quTitle;
	private String quContent;
	private int quIsread;
	
	public BoardDTO() {
	}

	public BoardDTO(int quNo, String userId, String userName, String quTitle, String quContent, int quIsread) {
		this.quNo = quNo;
		this.userId = userId;
		this.userName = userName;
		this.quTitle = quTitle;
		this.quContent = quContent;
		this.quIsread = quIsread;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "BoardDTO [quNo=" + quNo + ", userId=" + userId + ", quTitle=" + quTitle + ", quContent=" + quContent
				+ ", quIsread=" + quIsread + "]";
	}

	public int getQuNo() {
		return quNo;
	}

	public void setQuNo(int quNo) {
		this.quNo = quNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getQuTitle() {
		return quTitle;
	}

	public void setQuTitle(String quTitle) {
		this.quTitle = quTitle;
	}

	public String getQuContent() {
		return quContent;
	}

	public void setQuContent(String quContent) {
		this.quContent = quContent;
	}

	public int getQuIsread() {
		return quIsread;
	}

	public void setQuIsread(int quIsread) {
		this.quIsread = quIsread;
	}
	
	
	
	
}
