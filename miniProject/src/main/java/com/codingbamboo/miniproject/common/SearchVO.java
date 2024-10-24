package com.codingbamboo.miniproject.common;

public class SearchVO {
	private String searchOption;		/* �˻� ����(����title, ����content, �ۼ���name) */
	private String searchWord;			/* �˻��� */
	private int boardCount;				/* Q&A �Խñ� ���� */
	private int noticeCount;			/* �������� �Խñ� ���� */
	
	private int pageNo = 1;				/* ���� ������ ��ȣ */
	private int rowSizePerPage = 30;	/* �� ���������� ������ �Խñ� �� */
	
	// ���� ������ ���۰� �� �� ��ȣ
	private int start;
	private int end;
	
	// ȭ�鿡 �׷����� ������ ���� ���ڿ� ������ ����
	private int firstPage;
	private int lastPage;
	
	// ������ ������ ����
	private int finalPage;
	
	public SearchVO() {
	}
	
	public void boardSetting() {
		start = rowSizePerPage * (pageNo - 1) + 1;
		end = rowSizePerPage * pageNo;
		
		firstPage = ((pageNo-1) / 30) * 30 + 1;
		lastPage = firstPage + 29;
		
		finalPage = (int)Math.ceil((double)boardCount / rowSizePerPage);
		
		if(lastPage > finalPage) {
			lastPage = finalPage;
		}
	}
	
	public void noticeSetting() {
		start = rowSizePerPage * (pageNo - 1) + 1;
		end = rowSizePerPage * pageNo;
		
		firstPage = ((pageNo-1) / 30) * 30 + 1;
		lastPage = firstPage + 29;
		
		finalPage = (int)Math.ceil((double)noticeCount / rowSizePerPage);
		
		if(lastPage > finalPage) {
			lastPage = finalPage;
		}
	}

	public SearchVO(String searchOption, String searchWord, int boardCount, int noticeCount, int pageNo,
			int rowSizePerPage, int start, int end, int firstPage, int lastPage, int finalPage) {
		super();
		this.searchOption = searchOption;
		this.searchWord = searchWord;
		this.boardCount = boardCount;
		this.noticeCount = noticeCount;
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
				+ ", noticeCount=" + noticeCount + ", pageNo=" + pageNo + ", rowSizePerPage=" + rowSizePerPage
				+ ", start=" + start + ", end=" + end + ", firstPage=" + firstPage + ", lastPage=" + lastPage
				+ ", finalPage=" + finalPage + "]";
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

	public int getNoticeCount() {
		return noticeCount;
	}

	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
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
