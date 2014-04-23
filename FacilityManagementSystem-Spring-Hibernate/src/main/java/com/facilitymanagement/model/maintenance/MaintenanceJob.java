package com.facilitymanagement.model.maintenance;

public class MaintenanceJob implements IMaintenanceJob {	
	
	private int jobId = 0;
	private String jobDescription;
	private String jobPerformer;
	private double price;
	//added for db purposes 
	private int detailId;
	
	public MaintenanceJob(){}
	
	public MaintenanceJob(int jobId, String jobDescription, String jobPerformer, double price){
		this.jobId = jobId;
		this.jobDescription = jobDescription;
		this.jobPerformer= jobPerformer;
		this.price = price;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public double getPrice(){
		return price;
	}
	
	public int getJobId(){
		return jobId;
	}
	
	public void setJobId(int jobId){
		this.jobId = jobId;
	}
	
	public void setJobDescription(String jobDescription){
		this.jobDescription = jobDescription;
	}
	
	public String getJobDescription(){
		return jobDescription;
	}
	
	public void setJobPerformer(String jobPerformer){
		this.jobPerformer = jobPerformer;
	}
	
	public String getJobPerformer(){
		return jobPerformer;
	}
	
	public void setDetailId(int detailId){
		this.detailId = detailId;
	}
	
	public int getDetailId(){
		return detailId;
	}

	public String toString(){
		String details = "Job ID: \t" + this.jobId + "\n" +
						"Performed By: \t" + this.jobPerformer + "\n" +
						"Description: \t" + this.jobDescription + "\n" +
						"Cost: \t$" + this.price;
		return details;
	}
}
