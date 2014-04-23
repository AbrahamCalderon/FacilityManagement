package com.facilitymanagement.model.user;

public interface IOwner extends IUser {	
	void setOwnerId(int ownerId);	
	int getOwnerId();
	public void setFacilityId(int facilityId);
	public int getFacilityId();
	void setCompany(String company);	
	String getCompany();
}
