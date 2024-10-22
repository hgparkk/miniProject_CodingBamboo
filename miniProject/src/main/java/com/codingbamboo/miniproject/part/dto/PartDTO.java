package com.codingbamboo.miniproject.part.dto;

public class PartDTO {

	private String peLargeField;
	private String peMiddleField;
	private String peSmallField;
	private String pePart;
	private int peYear;
	private int yearTo;
	private int yearFrom;
	private double peEmission;
	
	public PartDTO() {
		super();
	}
	public PartDTO(String peLargeField, String peMiddleField, String peSmallField, String pePart, int peYear,
			int yearTo, int yearFrom, double peEmission) {
		super();
		this.peLargeField = peLargeField;
		this.peMiddleField = peMiddleField;
		this.peSmallField = peSmallField;
		this.pePart = pePart;
		this.peYear = peYear;
		this.yearTo = yearTo;
		this.yearFrom = yearFrom;
		this.peEmission = peEmission;
	}
	
	@Override
	public String toString() {
		return "PartDTO [peLargeField=" + peLargeField + ", peMiddleField=" + peMiddleField + ", peSmallField="
				+ peSmallField + ", pePart=" + pePart + ", peYear=" + peYear + ", yearTo=" + yearTo + ", yearFrom="
				+ yearFrom + ", peEmission=" + peEmission + "]";
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
		return yearTo;
	}
	public void setYearTo(int yearTo) {
		this.yearTo = yearTo;
	}
	public int getYearFrom() {
		return yearFrom;
	}
	public void setYearFrom(int yearFrom) {
		this.yearFrom = yearFrom;
	}
	public double getPeEmission() {
		return peEmission;
	}
	public void setPeEmission(double peEmission) {
		this.peEmission = peEmission;
	}
}