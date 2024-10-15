package com.codingbamboo.miniproject.common;

public class SearchVO {
	private String searchOption;	/* 검색 종류(제목title, 내용content, 작성자name) */
	private String searchWord;		/* 검색어 */
	private int materialCount;
	
	public SearchVO() {
	}

	public SearchVO(String searchOption, String searchWord, int materialCount) {
		this.searchOption = searchOption;
		this.searchWord = searchWord;
		this.materialCount = materialCount;
	}

	@Override
	public String toString() {
		return "SearchVO [searchOption=" + searchOption + ", searchWord=" + searchWord + ", materialCount="
				+ materialCount + "]";
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

	public int getMaterialCount() {
		return materialCount;
	}

	public void setMaterialCount(int materialCount) {
		this.materialCount = materialCount;
	}
	
	
	
	
}
