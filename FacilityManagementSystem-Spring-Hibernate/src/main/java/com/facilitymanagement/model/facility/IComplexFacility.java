package com.facilitymanagement.model.facility;

import java.util.List;

import com.facilitymanagement.model.user.IOwner;

public interface IComplexFacility extends IFacility {
	String getStreet();
	void setStreet(String street);
	String getCity();
	void setCity(String city);
	String getState();
	void setState(String state);
	String getZip();
	void setZip(String zip);
	void setFacilityStatus(FacilityStatus facilityStatus);
	FacilityStatus getFacilityStatus();
	IFacilityDetail getDetail();
	void setDetail(IFacilityDetail detail);
	void addDetail(String detail);	
	String getUsage();
	void setInspections(List<IInspection> inspections);
	List<IInspection> getInspections();
	void setUnits(List<IAptUnit> units);
	List<IAptUnit> getUnits();
	void addUnit(IAptUnit newUnit);
	void removeUnit(IAptUnit remUnit);
	void setFeatures(List<IFeature> features);
	List<IFeature> getFeatures();
	
	//additions
	void setOwner(IOwner owner);
	IOwner getOwner();
}
