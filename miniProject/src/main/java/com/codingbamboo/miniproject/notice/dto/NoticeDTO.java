package com.codingbamboo.miniproject.notice.dto;

public class NoticeDTO {
	private int noNo;
	private String noTitle;
	private String noContent;
	private int noTop;
	
	public NoticeDTO() {
	}

	public NoticeDTO(int noNo, String noTitle, String noContent, int noTop) {
		this.noNo = noNo;
		this.noTitle = noTitle;
		this.noContent = noContent;
		this.noTop = noTop;
	}

	@Override
	public String toString() {
		return "NoticeDTO [noNo=" + noNo + ", noTitle=" + noTitle + ", noContent=" + noContent + ", noTop=" + noTop
				+ "]";
	}

	public int getNoNo() {
		return noNo;
	}

	public void setNoNo(int noNo) {
		this.noNo = noNo;
	}

	public String getNoTitle() {
		return noTitle;
	}

	public void setNoTitle(String noTitle) {
		this.noTitle = noTitle;
	}

	public String getNoContent() {
		return noContent;
	}

	public void setNoContent(String noContent) {
		this.noContent = noContent;
	}

	public int getNoTop() {
		return noTop;
	}

	public void setNoTop(int noTop) {
		this.noTop = noTop;
	}

	
}