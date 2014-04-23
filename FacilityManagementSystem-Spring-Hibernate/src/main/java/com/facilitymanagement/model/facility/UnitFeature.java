package com.facilitymanagement.model.facility;

public class UnitFeature implements IFeature{
	
	private int featureId;
	private int aptId;
	private String featureDescription;
	
	public UnitFeature(){}	
	
	public UnitFeature(String featureDescription){
		this.featureDescription = featureDescription;
	}
	public void setFeatureId(int featureId){
		this.featureId = featureId;
	}
	
	public int getFeatureId(){
		return featureId;
	}
	public void setAptId(int aptId){
		this.aptId = aptId;
	}
	public int getAptId(){
		return aptId;
	}
	public void setFeatureDescription(String featureDescription){
		this.featureDescription = featureDescription;
	}
	public String getFeatureDescription(){
		return featureDescription;
	}
}
