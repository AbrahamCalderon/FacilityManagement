package com.facilitymanagement.model.facility;

import java.util.List;

public interface IUnitDetail {
	void setAptDetId(int id);	
	int getAptDetId();
	void setAptId(int aptId);
	int getAptId();
	void setLength(double length);
	double getLength();
	void setWidth(double width);
	double getWidth();
	void setNumRooms(int numRooms);
	public int getNumRooms();
	void setNumBaths(double numBaths);
	double getNumBaths();	
	void setMoreDetail(String moreDetail);
	String getMoreDetail();
	void addMoreInfo(String moreDetail);	
	double getSquareFeet();
	StringBuilder getDetails();
}
