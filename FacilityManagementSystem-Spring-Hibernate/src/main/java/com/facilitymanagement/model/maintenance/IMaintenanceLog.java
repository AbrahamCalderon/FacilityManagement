package com.facilitymanagement.model.maintenance;

import java.sql.Date;

public interface IMaintenanceLog {
	public void setLogId(int logId);
	public int getLogId();
	void setSeverityLevel(SeverityLevel severityLevel);	
	SeverityLevel getSeverityLevel();
	void setMaintCat(MaintenanceCat maintCat);
	MaintenanceCat getMaintCat();	
	void setDate(Date date);
	public Date getDate();
	void setDuration(int min);
	public int getDuration();
	void setOrder(IOrder order);	
	IOrder getOrder();
}
