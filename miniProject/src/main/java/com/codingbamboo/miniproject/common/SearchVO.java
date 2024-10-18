package com.codingbamboo.miniproject.common;

public class SearchVO {
	private String searchOption;		/* 검색 종류(제목title, 내용content, 작성자name) */
	private String searchWord;			/* 검색어 */
	private int boardCount;
	
	private int pageNo = 1;				/* 현재 페이지 번호 */
	private int rowSizePerPage = 10;	/* 한 페이지에서 보여줄 게시글 수 */
	
	// 현재 페이지 시작과 끝 글 번호
	private int start;
	private int end;
	
	// 화면에 그려지는 페이지 시작 숫자와 마지막 숫자
	private int firstPage;
	private int lastPage;
	
	// 마지막 페이지 숫자
	private int finalPage;
	
	public SearchVO() {
	}
	
	public void setting() {
		start = rowSizePerPage * (pageNo - 1) + 1;
		end = rowSizePerPage * pageNo;
		
		firstPage = ((pageNo-1) / 10) * 10 + 1;
		lastPage = firstPage + 9;
		
		finalPage = (int)Math.ceil((double)boardCount / rowSizePerPage);
		
		if(lastPage > finalPage) {
			lastPage = finalPage;
		}
	}

	public SearchVO(String searchOption, String searchWord, int boardCount, int pageNo, int rowSizePerPage, int start,
			int end, int firstPage, int lastPage, int finalPage) {
		this.searchOption = searchOption;
		this.searchWord = searchWord;
		this.boardCount = boardCount;
		this.pageNo = pageNo;
		this.rowSizePerPage = rowSizePerPage;
		this.start = start;
		this.end = end;
		this.firstPage = firstPage;
		this.lastPage = lastPage;
		this.finalPage = finalPage;
	}

	@Override
	public String toString() {
		return "SearchVO [searchOption=" + searchOption + ", searchWord=" + searchWord + ", boardCount=" + boardCount
				+ ", pageNo=" + pageNo + ", rowSizePerPage=" + rowSizePerPage + ", start=" + start + ", end=" + end
				+ ", firstPage=" + firstPage + ", lastPage=" + lastPage + ", finalPage=" + finalPage + "]";
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

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getRowSizePerPage() {
		return rowSizePerPage;
	}

	public void setRowSizePerPage(int rowSizePerPage) {
		this.rowSizePerPage = rowSizePerPage;
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

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getFinalPage() {
		return finalPage;
	}

	public void setFinalPage(int finalPage) {
		this.finalPage = finalPage;
	}
}
