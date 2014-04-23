package com.facilitymanagement.model.facility;

import java.sql.Date;

public interface IInspection {
	
	public int getInspectionId();	
	void setInspectionId(int inspectionId);
	//public int getFacilityId();
	//void setFacilityId(int facilityId);
	void setCategory(InspectionType category);
	InspectionType getCategory();	
	void setDate(Date date);
	Date getDate();
	void setInspector(String inspector);
	String getInspector();
	void setResult(InspectionResult result);
	InspectionResult getResult();
	void setComment(String comment);
	String getComment();
	
}
