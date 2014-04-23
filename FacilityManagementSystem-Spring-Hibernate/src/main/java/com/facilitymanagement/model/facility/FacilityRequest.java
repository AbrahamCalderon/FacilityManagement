package com.facilitymanagement.model.facility;

import java.sql.Date;
import java.sql.Time;
import com.facilitymanagement.model.facility.RequestStatus;
import com.facilitymanagement.model.maintenance.MaintenanceCat;

public class FacilityRequest implements IRequest {
	
	private int requestId;
	private IComplexFacility facility;
	private IAptUnit unit;
	private Date dateRequested;
	private Time timeRequested;
	private MaintenanceCat category;
	private String description;
	protected RequestStatus requestStatus;
	private String requester;
	
	
	public FacilityRequest() {}
	
	public FacilityRequest(IComplexFacility facility, IAptUnit unit, Date dateRequested, Time timeRequested, 
			MaintenanceCat category, String description, RequestStatus requestStatus){
		this.facility = facility;
		this.unit = unit;
		this.dateRequested = dateRequested;
		this.timeRequested = timeRequested;
		this.category = category;
		this.description = description;
		this.requestStatus = requestStatus;
	}
	
	public FacilityRequest(IComplexFacility facility, Date dateRequested, Time timeRequested, 
			MaintenanceCat category, String description, RequestStatus requestStatus){
		this.facility = facility;
		this.dateRequested = dateRequested;
		this.timeRequested = timeRequested;
		this.category = category;
		this.description = description;
		this.requestStatus = requestStatus;
		
	}
	
	public void setRequestId(int requestId){
		this.requestId = requestId;
	}
	
	public int getRequestId(){
		return requestId;
	}
	
	public void setFacility(IComplexFacility facility){
		this.facility = facility;
	}
	
	public IComplexFacility getFacility(){
		return facility;
	}
	
	public void setUnit(IAptUnit unit){
		this.unit = unit;
	}
	
	public IAptUnit getUnit(){
		return null;
	}
	

	public void setDateRequested(Date dateRequested){
		this.dateRequested = dateRequested;
	}
	
	public Date getDateRequested(){
		return dateRequested;
	}
	
	public void setRequestStatus(RequestStatus requestStatus){
		this.requestStatus = requestStatus;
	}
	
	public RequestStatus getRequestStatus(){
		return requestStatus;
	}
	
	public void setTimeRequested(Time timeRequested){
		this.timeRequested = timeRequested;
	}
	
	public Time getTimeRequested(){
		return timeRequested;
	}
	
	public MaintenanceCat getCategory(){
		return category;
	}
	
	public void setCategory(MaintenanceCat category){
		this.category = category;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return description;
	}
	
	public String toString(){
		return "REQUEST DETAILS\n" + 
				"Facility ID: " + facility.getFacilityId() +" Unit ID: " + unit.getUnitId() +
				"\nDate:" + dateRequested + 
				"\nTime: " + timeRequested + "\nType: " + category +
				"\nDescription: " + description +
				"\nStatus: " + requestStatus;
	}

	public void setRequester(String name) {
		this.requester = name;
		
	}

	public String getRequester() {
		return requester;
	}
}
