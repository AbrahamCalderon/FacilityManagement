package com.facilitymanagement.model.maintenance;

import java.sql.Date;

public class TimeRecord {

	private Date date = null;
	private int duration;
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public Date getDate(){
		return date;
	}
	
	public void setDuration(int hrs){
		this.duration = hrs;
	}
	
	public int getDuration(){
		return duration;
	}
}
