package com.facilitymanagement.model.facility;

public interface IFacility {
	String getFacilityName();
	void setFacilityName(String facilityName);
	int getFacilityId();
	void setFacilityId(int facId);
	int requestAvailableCapacity();
	void getFacilityInformation();
}
