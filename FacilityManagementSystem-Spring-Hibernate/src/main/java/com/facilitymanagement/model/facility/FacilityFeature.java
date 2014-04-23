package com.facilitymanagement.model.facility;

public class FacilityFeature implements IFeature{
	
	private int featureId;
	private int facId;
	private String featureDescription = "";
	
	public FacilityFeature(){}	
	
	public FacilityFeature(String featureDescription){
		this.featureDescription = featureDescription;
	}
	public void setFeatureId(int featureId){
		this.featureId = featureId;
	}
	
	public int getFeatureId(){
		return featureId;
	}
	public void setFacId(int facId){
		this.facId = facId;
	}
	public int getFacId(){
		return facId;
	}
	public void setFeatureDescription(String featureDescription){
		this.featureDescription = featureDescription;
	}
	public String getFeatureDescription(){
		return featureDescription;
	}
}
