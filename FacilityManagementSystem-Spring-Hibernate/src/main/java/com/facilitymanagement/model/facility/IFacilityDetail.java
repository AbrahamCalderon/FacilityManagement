package com.facilitymanagement.model.facility;

import java.util.List;

import com.facilitymanagement.model.user.IOwner;
import com.facilitymanagement.model.user.Owner;

public interface IFacilityDetail {	
	StringBuilder getDetails();
	void setFacDetId(int facDetId);
	int getFacDetId();
	public void setFacId(int facId);
	public int getFacId();
	void setYearBuilt(String yearBuilt);
	String getYearBuilt();
	void setUsage(String usages);
	String getUsage();
	void setAdditionalDetails(String additionalDetails);
	String getAdditionalDetails();
	void addDetails(String moreDetails);
	//StringBuilder displayUsages();
}
