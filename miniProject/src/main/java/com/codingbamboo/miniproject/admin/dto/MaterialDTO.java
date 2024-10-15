package com.codingbamboo.miniproject.admin.dto;

public class MaterialDTO {
	private int meNo;			/* ���纰 ��ȣ */
	private String meName;		/* ���� �̸� */
	private double meEmission;	/* ���� kg�� ź�ҹ��ⷮ */
	
	public MaterialDTO() {
		super();
	}

	public MaterialDTO(int meNo, String meName, int meEmission) {
		super();
		this.meNo = meNo;
		this.meName = meName;
		this.meEmission = meEmission;
	}

	public int getMeNo() {
		return meNo;
	}

	public void setMeNo(int meNo) {
		this.meNo = meNo;
	}

	public String getMeName() {
		return meName;
	}

	public void setMeName(String meName) {
		this.meName = meName;
	}

	

	public double getMeEmission() {
		return meEmission;
	}

	public void setMeEmission(double meEmission) {
		this.meEmission = meEmission;
	}

	public void setMeEmission(int meEmission) {
		this.meEmission = meEmission;
	}

	@Override
	public String toString() {
		return "AdminDTO [meNo=" + meNo + ", meName=" + meName + ", meEmission=" + meEmission + "]";
	}
}
