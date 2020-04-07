package com.ps.mocorongavirusranking.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CountryCorona {
	
	private String country;
	private int cases;
	private int deaths;
	private int recoveries;
	@JsonIgnore
	private Date firstDateConfirmedCase;
	private String image;
	
	public String getCountry() {
		return country;
	}
	public int getCases() {
		return cases;
	}
	public int getDeaths() {
		return deaths;
	}
	public int getRecoveries() {
		return recoveries;
	}
	public Date getFirstDateConfirmedCase() {
		return firstDateConfirmedCase;
	}
	public String getImage () {
		return image;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setCases(int cases) {
		this.cases = cases;
	}
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	public void setRecoveries(int recoveries) {
		this.recoveries = recoveries;
	}
	public void setFirstDateConfirmedCase(Date firstDateConfirmedCase) {
		this.firstDateConfirmedCase = firstDateConfirmedCase;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
