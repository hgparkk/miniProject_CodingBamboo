package com.codingbamboo.miniproject.common;

public class SearchVO {
	private String searchOption;	/* �˻� ����(����title, ����content, �ۼ���name) */
	private String searchWord;		/* �˻��� */
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
