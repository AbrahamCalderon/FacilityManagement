package com.facilitymanagement.model.facility;

import java.util.ArrayList;
import java.util.List;

import com.facilitymanagement.model.user.IOwner;

public class FacilityDetail implements IFacilityDetail{

	private String yearBuilt;
	private String usage;
	private String additionalDetails;
	private int facDetId;
	private int facId;
	
	public FacilityDetail(String yr){
		this.yearBuilt = yr;
	}
	
	public FacilityDetail() {}

	public StringBuilder getDetails() {
		StringBuilder sb = new StringBuilder();
		sb.append("FACILITY DETAILS: ");
		sb.append("\nYear Built: " + yearBuilt);
		sb.append("\nUsage: " + usage);
		sb.append("\nAdditional Details: " + additionalDetails);
		return sb;
	}
	public void setFacDetId(int facDetId){
		this.facDetId = facDetId;
	}
	
	public int getFacDetId(){
		return facDetId;
	}
	
	public void setFacId(int facId){
		this.facId = facId;
	}
	
	public int getFacId(){
		return facId;
	}
	
	public void setYearBuilt(String yearBuilt){
		this.yearBuilt = yearBuilt;
	}
	public String getYearBuilt(){
		return yearBuilt;
	}
	
	public void setUsage(String usages){
		this.usage = usages;
	}
	
	public String getUsage(){
		return usage;
	}
	
	public void setAdditionalDetails(String additionalDetails){
		this.additionalDetails = additionalDetails;
	}
	
	public String getAdditionalDetails(){
		return additionalDetails;
	}

	
	public void addDetails(String moreDetails) {
		additionalDetails += moreDetails;
	}
	/*
	public StringBuilder displayUsages(){
		StringBuilder sb = new StringBuilder();
		for(String s : usages){
			sb.append("\n - " + s);
		}
		return sb;
	}
	*/

}
