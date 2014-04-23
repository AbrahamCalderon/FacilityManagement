package com.facilitymanagement.model.maintenance;

import java.sql.Date;

public class MaintenanceLog implements IMaintenanceLog {

	private int logId;
	private MaintenanceCat maintCat;
	private SeverityLevel severityLevel;
	private Date date = null;
	private int duration;
	private IOrder order;
	
	public void setLogId(int logId){
		this.logId = logId;
	}
	
	public int getLogId(){
		return logId;
	}
	
	public void setSeverityLevel(SeverityLevel severityLevel){
		this.severityLevel = severityLevel;
	}
	
	public SeverityLevel getSeverityLevel(){
		return severityLevel;
	}
	
	public void setMaintCat(MaintenanceCat maintCat){
		this.maintCat = maintCat;
	}
	
	public MaintenanceCat getMaintCat(){
		return maintCat;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public Date getDate(){
		return date;
	}
	
	public void setDuration(int min){
		this.duration = min;
	}
	
	public int getDuration(){
		return duration;
	}
	
	public void setOrder(IOrder order){
		this.order = order;
	}
	
	public IOrder getOrder(){
		return order;
	}
}
