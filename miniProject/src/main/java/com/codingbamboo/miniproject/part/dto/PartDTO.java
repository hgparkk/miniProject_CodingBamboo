package com.codingbamboo.miniproject.part.dto;

public class PartDTO {

	private String peLargeField;
	private String peMiddleField;
	private String peSmallField;
	private String pePart;
	private int peYear;
	private int YearTo;
	private int YearFrom;
	private double peEmissions;
	
	public PartDTO() {
		super();
	}
	public PartDTO(String peLargeField, String peMiddleField, String peSmallField, String pePart, int peYear,
			int yearTo, int yearFrom, double peEmissions) {
		super();
		this.peLargeField = peLargeField;
		this.peMiddleField = peMiddleField;
		this.peSmallField = peSmallField;
		this.pePart = pePart;
		this.peYear = peYear;
		YearTo = yearTo;
		YearFrom = yearFrom;
		this.peEmissions = peEmissions;
	}
	
	@Override
	public String toString() {
		return "PartDTO [peLargeField=" + peLargeField + ", peMiddleField=" + peMiddleField + ", peSmallField="
				+ peSmallField + ", pePart=" + pePart + ", peYear=" + peYear + ", YearTo=" + YearTo + ", YearFrom="
				+ YearFrom + ", peEmissions=" + peEmissions + "]";
	}
	
	public String getPeLargeField() {
		return peLargeField;
	}
	public void setPeLargeField(String peLargeField) {
		this.peLargeField = peLargeField;
	}
	public String getPeMiddleField() {
		return peMiddleField;
	}
	public void setPeMiddleField(String peMiddleField) {
		this.peMiddleField = peMiddleField;
	}
	public String getPeSmallField() {
		return peSmallField;
	}
	public void setPeSmallField(String peSmallField) {
		this.peSmallField = peSmallField;
	}
	public String getPePart() {
		return pePart;
	}
	public void setPePart(String pePart) {
		this.pePart = pePart;
	}
	public int getPeYear() {
		return peYear;
	}
	public void setPeYear(int peYear) {
		this.peYear = peYear;
	}
	public int getYearTo() {
		return YearTo;
	}
	public void setYearTo(int yearTo) {
		YearTo = yearTo;
	}
	public int getYearFrom() {
		return YearFrom;
	}
	public void setYearFrom(int yearFrom) {
		YearFrom = yearFrom;
	}
	public double getPeEmissions() {
		return peEmissions;
	}
	public void setPeEmissions(double peEmissions) {
		this.peEmissions = peEmissions;
	}
	
	
}


