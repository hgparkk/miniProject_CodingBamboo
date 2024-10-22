package com.codingbamboo.miniproject.notice.dto;

public class NoticeDTO {
	private int noNo;
	private String noTitle;
	private String noContent;
	
	public NoticeDTO() {
		super();
	}

	public NoticeDTO(int noNo, String noTitle, String noContent) {
		super();
		this.noNo = noNo;
		this.noTitle = noTitle;
		this.noContent = noContent;
	}

	@Override
	public String toString() {
		return "NoticeDTO [noNo=" + noNo + ", noTitle=" + noTitle + ", noContent=" + noContent + "]";
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
}