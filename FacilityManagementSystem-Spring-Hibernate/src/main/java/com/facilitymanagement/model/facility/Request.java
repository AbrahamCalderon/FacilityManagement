package com.facilitymanagement.model.facility;

import java.sql.Date;
import java.sql.Time;

import com.facilitymanagement.model.maintenance.MaintenanceCat;

public class Request implements IRequest {	
	private int requestId;
	private IComplexFacility facility;
	private IAptUnit unit;
	private Date dateRequested;
	private Time timeRequested;
	private MaintenanceCat category;
	private String description;
	private String requester;
	protected RequestStatus requestStatus = RequestStatus.PENDING;
	
	public Request() {}
	
	public Request(IComplexFacility facility, IAptUnit unit, Date dateRequested, Time timeRequested, 
			MaintenanceCat category, String description, RequestStatus requestStatus, String name){
		this.facility = facility;
		this.unit = unit;
		this.dateRequested = dateRequested;
		this.timeRequested = timeRequested;
		this.category = category;
		this.description = description;
		this.requestStatus = requestStatus;
		this.requester = name;
	}
	
	public void setRequester(String name){
		this.requester = name;
	}
	
	public String getRequester(){
		return requester;
	}
	
	public Request(IComplexFacility facility, Date dateRequested, Time timeRequested, 
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
		return unit;
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
		String id = "";
		if(this.unit != null){
			int x = this.unit.getUnitId();
			id = String.valueOf(x);
		}
		return "REQUEST DETAILS\n" + 
				"Facility ID: " + facility.getFacilityId() +" Unit ID: " + id +
				"\nDate:" + dateRequested + 
				"\nTime: " + timeRequested + "\nType: " + category +
				"\nDescription: " + description +
				"\nStatus: " + requestStatus;
	}
}
