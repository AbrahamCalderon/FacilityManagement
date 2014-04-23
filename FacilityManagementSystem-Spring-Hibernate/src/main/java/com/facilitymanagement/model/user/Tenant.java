package com.facilitymanagement.model.user;

//import com.facilitymanagement.model.user.Sex;

public class Tenant implements ITenant {	
	
	private int tenantId;
	private String firstName;
	private String lastName;
	private String dob;
	private String ssn;
	private Sex sex = Sex.FEMALE;
	private String leaseId; //Hibernate compromise <one-to-one>
	
	public Tenant() {}
	
	public Tenant(int tenantId, String firstName, String lastName, String dob, String ssn, Sex sex){
		this.tenantId = tenantId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.ssn = ssn;
		this.sex = sex;
	}	

	public void setTenantId(int tenantId){
		this.tenantId = tenantId;
	}
	public int getTenantId(){
		return tenantId;
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
	
	public void setDob(String bdate){
		this.dob = bdate;
	}
	
	public String getDob(){
		return dob;
	}
	
	public void setSsn(String ssn){
		this.ssn = ssn;
	}
	
	public String getSsn(){
		return ssn;
	}
	
	public void setSex(Sex sex){
		this.sex = sex;
	}
	
	public Sex getSex(){
		return sex;
	}

	public String toString(){
		String tenant = "\nTenant Info: " + "\n" + this.lastName + ", " + this.firstName + "\n" +
				"SSN: " + this.ssn + "\n" +
				"Sex: " + this.sex + "\n" +
				"TenantID: " + this.tenantId + "\n" +
				"Lease No.: " + this.leaseId;
		return tenant;
	}	

	public void setLeaseId(String leaseId){
		this.leaseId = leaseId;
	}
	
	public String getLeaseId(){
		return leaseId;
	}
	
	public String getUserInfo() {
		// TODO Auto-generated method stub
		return this.toString();
	}
}
