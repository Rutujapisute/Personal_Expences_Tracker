package com.example.Expences_TrackerUsingJWT.Model;


public class MonthRequest {

    private int yyyy;
    private int mm;
    private String category;
    // Default constructor
	public int getYyyy() {
		return yyyy;
	}
	public void setYyyy(int yyyy) {
		this.yyyy = yyyy;
	}
	public int getMm() {
		return mm;
	}
	public void setMm(int mm) {
		this.mm = mm;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public MonthRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
   
    
    
}