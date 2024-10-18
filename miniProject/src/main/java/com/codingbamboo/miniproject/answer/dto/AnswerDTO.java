package com.codingbamboo.miniproject.answer.dto;

public class AnswerDTO {
	private int awNo;
	private int quNo;
	private String awContent;
	
	public AnswerDTO() {
	}

	public AnswerDTO(int awNo, int quNo, String awContent) {
		this.awNo = awNo;
		this.quNo = quNo;
		this.awContent = awContent;
	}

	@Override
	public String toString() {
		return "AnswerDTO [awNo=" + awNo + ", quNo=" + quNo + ", awContent=" + awContent + "]";
	}

	public int getAwNo() {
		return awNo;
	}

	public void setAwNo(int awNo) {
		this.awNo = awNo;
	}

	public int getQuNo() {
		return quNo;
	}

	public void setQuNo(int quNo) {
		this.quNo = quNo;
	}

	public String getAwContent() {
		return awContent;
	}

	public void setAwContent(String awContent) {
		this.awContent = awContent;
	}
}
