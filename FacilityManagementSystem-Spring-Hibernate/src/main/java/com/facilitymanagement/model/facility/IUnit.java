package com.facilitymanagement.model.facility;

public interface IUnit {
	int getUnitId();
	void setUnitId(int unitId);
	int getCapacity();
	void setCapacity(int capacity);
	double calculateSquareFootage();
	UnitStatus getStatus();
	void setStatus(UnitStatus status);	
}
