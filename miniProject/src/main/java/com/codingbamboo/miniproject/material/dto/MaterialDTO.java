package com.codingbamboo.miniproject.material.dto;

public class MaterialDTO {
	private int meNo;           /* 자재별 번호 */
	private String meName;		/* 자재 이름 */
	private double meEmission;	/* 자재 kg당 탄소배출량 */
	
	public MaterialDTO() {
		super();
	}

	public MaterialDTO(int meNo, String meName, double meEmissions) {
		super();
		this.meNo = meNo;
		this.meName = meName;
		this.meEmission = meEmissions;
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

	@Override
	public String toString() {
		return "MaterialDTO [meNo=" + meNo + ", meName=" + meName + ", meEmission=" + meEmission + "]";
	}
}