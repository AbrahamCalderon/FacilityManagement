package com.facilitymanagement.model.facility;

import java.sql.Date;

public class Inspection implements IInspection{
	private int inspectionId;
	//private int facilityId;
	private InspectionType category = InspectionType.AIR_CONDITIONING;
	private Date date;
	private String inspector;
	private InspectionResult result = InspectionResult.PASS;
	private String comment;
	
	public Inspection(){}
	
	public Inspection(InspectionType category, Date date, String inspector, InspectionResult result, String comment){
		this.category = category;
		this.date = date;
		this.inspector = inspector;
		this.result = result;
		this.comment = comment;
	}
	/*
	public void setFacilityId(int facilityId){
		this.facilityId = facilityId;
	}
	
	public int getFacilityId(){
		return facilityId;
	}
	*/
	public int getInspectionId(){
		return inspectionId;
	}
	
	public void setInspectionId(int inspectionId){
		this.inspectionId = inspectionId;
	}
	
	public void setCategory(InspectionType category){
		this.category = category;
	}
	
	public InspectionType getCategory(){
		return category;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	public Date getDate(){
		return date;
	}
	public void setInspector(String inspector){
		this.inspector = inspector;
	}
	public String getInspector(){
		return inspector;
	}
	public void setResult(InspectionResult result){
		this.result = result;
	}
	public InspectionResult getResult(){
		return result;
	}
	public void setComment(String comment){
		this.comment = comment;
	}
	public String getComment(){
		return comment;
	}
	
	@Override
	public String toString(){
		return "Inspection ID: " + this.inspectionId + "\nCategory: " + this.category + "\nDate: " + this.date +
				"\nInspected by: " + this.inspector + "\nResult: " + this.result + "\nComments: " + this.comment;
	}

}
