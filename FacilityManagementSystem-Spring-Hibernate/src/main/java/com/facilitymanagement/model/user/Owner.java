package com.facilitymanagement.model.user;

public class Owner implements IOwner {
	
	private int ownerId;
	private int facilityId;
	private String firstName;
	private String lastName;
	private String company;	
	
	public Owner() {}
	
	public void setFacilityId(int facilityId){
		this.facilityId = facilityId;
	}
	
	public int getFacilityId(){
		return facilityId;
	}
	
	public Owner(String company){
		this.company = company;
	}
	
	public Owner(String fname, String lname){
		this.firstName = fname;
		this.lastName = lname;
	}
	
	public void setOwnerId(int ownerId){
		this.ownerId = ownerId;
	}
	
	public int getOwnerId(){
		return ownerId;
	}

	public void setFirstName(String fname){
		this.firstName = fname;
	}
	public String getFirstName(){
		return firstName;
	}
	
	public void setLastName(String lname){
		this.lastName = lname;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public void setCompany(String company){
		this.company = company;
	}
	
	public String getCompany(){
		return company;
	}
	
	public String toString(){
		String owner = this.firstName + ", " + this.lastName + "\n"
				+ "Company: " + this.company;
		
		return owner;
	}
	
	public String getUserInfo() {
		return this.toString();
	}

}
