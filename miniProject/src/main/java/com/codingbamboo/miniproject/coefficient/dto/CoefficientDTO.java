package com.codingbamboo.miniproject.coefficient.dto;

public class CoefficientDTO {
	private int aecNo;
	private String aecName;
	private double aecCoefficient;
	
	public CoefficientDTO() {
		super();
	}

	public CoefficientDTO(int aecNo, String aecName, double aecCoefficient) {
		super();
		this.aecNo = aecNo;
		this.aecName = aecName;
		this.aecCoefficient = aecCoefficient;
	}

	public int getAecNo() {
		return aecNo;
	}

	public void setAecNo(int aecNo) {
		this.aecNo = aecNo;
	}

	public String getAecName() {
		return aecName;
	}

	public void setAecName(String aecName) {
		this.aecName = aecName;
	}

	public double getAecCoefficient() {
		return aecCoefficient;
	}

	public void setAecCoefficient(double aecCoefficient) {
		this.aecCoefficient = aecCoefficient;
	}

	@Override
	public String toString() {
		return "CoefficientDTO [aecNo=" + aecNo + ", aecName=" + aecName + ", aecCoefficient=" + aecCoefficient + "]";
	}
}