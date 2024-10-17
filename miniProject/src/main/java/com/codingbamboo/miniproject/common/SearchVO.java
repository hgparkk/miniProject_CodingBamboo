package com.codingbamboo.miniproject.common;

public class SearchVO {
	private String searchOption;	/* 검색 종류(제목title, 내용content, 작성자name) */
	private String searchWord;		/* 검색어 */
	private int boardCount;
	
	private int start;
	private int end;
	
	public SearchVO() {
	}
	
	public void setting() {
		end = boardCount;
	}

	public SearchVO(String searchOption, String searchWord, int boardCount, int start, int end) {
		super();
		this.searchOption = searchOption;
		this.searchWord = searchWord;
		this.boardCount = boardCount;
		this.start = start;
		this.end = end;
	}

	@Override
	public String toString() {
		return "SearchVO [searchOption=" + searchOption + ", searchWord=" + searchWord + ", boardCount=" + boardCount
				+ ", start=" + start + ", end=" + end + "]";
	}

	public String getSearchOption() {
		return searchOption;
	}

	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	

	
	
	
	
	
}
