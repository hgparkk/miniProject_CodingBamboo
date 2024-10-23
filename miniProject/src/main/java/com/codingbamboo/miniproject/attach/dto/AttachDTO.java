package com.codingbamboo.miniproject.attach.dto;

public class AttachDTO {
	private int atchNo;
	private int atchNoticeNo;
	private String atchFileName;
	private String atchName;
	private long atchFileSize;
	private String atchFancySize;
	private String atchContentType;
	private String atchPath;

	public AttachDTO() {
	}

	public AttachDTO(int atchNo, int atchNoticeNo, String atchFileName, String atchName, long atchFileSize,
			String atchFancySize, String atchContentType, String atchPath) {
		super();
		this.atchNo = atchNo;
		this.atchNoticeNo = atchNoticeNo;
		this.atchFileName = atchFileName;
		this.atchName = atchName;
		this.atchFileSize = atchFileSize;
		this.atchFancySize = atchFancySize;
		this.atchContentType = atchContentType;
		this.atchPath = atchPath;
	}

	@Override
	public String toString() {
		return "AttachDTO [atchNo=" + atchNo + ", atchNoticeNo=" + atchNoticeNo + ", atchFileName=" + atchFileName
				+ ", atchName=" + atchName + ", atchFileSize=" + atchFileSize + ", atchFancySize=" + atchFancySize
				+ ", atchContentType=" + atchContentType + ", atchPath=" + atchPath + "]";
	}

	public int getAtchNo() {
		return atchNo;
	}

	public void setAtchNo(int atchNo) {
		this.atchNo = atchNo;
	}

	public int getAtchNoticeNo() {
		return atchNoticeNo;
	}

	public void setAtchNoticeNo(int atchNoticeNo) {
		this.atchNoticeNo = atchNoticeNo;
	}

	public String getAtchFileName() {
		return atchFileName;
	}

	public void setAtchFileName(String atchFileName) {
		this.atchFileName = atchFileName;
	}

	public String getAtchName() {
		return atchName;
	}

	public void setAtchName(String atchName) {
		this.atchName = atchName;
	}

	public long getAtchFileSize() {
		return atchFileSize;
	}

	public void setAtchFileSize(long atchFileSize) {
		this.atchFileSize = atchFileSize;
	}

	public String getAtchFancySize() {
		return atchFancySize;
	}

	public void setAtchFancySize(String atchFancySize) {
		this.atchFancySize = atchFancySize;
	}

	public String getAtchContentType() {
		return atchContentType;
	}

	public void setAtchContentType(String atchContentType) {
		this.atchContentType = atchContentType;
	}

	public String getAtchPath() {
		return atchPath;
	}

	public void setAtchPath(String atchPath) {
		this.atchPath = atchPath;
	}

}
