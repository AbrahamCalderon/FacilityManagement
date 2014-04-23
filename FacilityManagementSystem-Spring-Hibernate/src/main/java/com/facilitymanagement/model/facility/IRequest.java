package com.facilitymanagement.model.facility;

import java.sql.Date;
import java.sql.Time;

import com.facilitymanagement.model.facility.RequestStatus;
import com.facilitymanagement.model.maintenance.MaintenanceCat;

public interface IRequest {
	void setRequestId(int requestId);
	int getRequestId();
	void setFacility(IComplexFacility facility);
	IComplexFacility getFacility();
	void setUnit(IAptUnit unit);
	IAptUnit getUnit();
	void setDateRequested(Date date);
	Date getDateRequested();
	void setRequestStatus(RequestStatus status);
	RequestStatus getRequestStatus();
	void setTimeRequested(Time timeRequested);
	Time getTimeRequested();
	void setCategory(MaintenanceCat category);
	MaintenanceCat getCategory();
	void setDescription(String description);
	String getDescription();
	void setRequester(String name);
	String getRequester();
}
