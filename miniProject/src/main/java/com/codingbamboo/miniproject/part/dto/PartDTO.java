package com.codingbamboo.miniproject.part.dto;

public class PartDTO {

	private String peLargeField;
	private String peMiddleField;
	private String peSmallField;
	private String pePart;
	private String peYear;
	private double peEmissions;
	
	public PartDTO(String peLargeField, String peMiddleField, String peSmallField, String pePart, String peYear,
			double peEmissions) {
		super();
		this.peLargeField = peLargeField;
		this.peMiddleField = peMiddleField;
		this.peSmallField = peSmallField;
		this.pePart = pePart;
		this.peYear = peYear;
		this.peEmissions = peEmissions;
	}

	public PartDTO() {
		super();
	}

	@Override
	public String toString() {
		return "PartDTO [peLargeField=" + peLargeField + ", peMiddleField=" + peMiddleField + ", peSmallField="
				+ peSmallField + ", pePart=" + pePart + ", peYear=" + peYear + ", peEmissions=" + peEmissions + "]";
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

	public String getPeYear() {
		return peYear;
	}

	public void setPeYear(String peYear) {
		this.peYear = peYear;
	}

	public double getPeEmissions() {
		return peEmissions;
	}

	public void setPeEmissions(double peEmissions) {
		this.peEmissions = peEmissions;
	}
	
	
	
	
	
}
