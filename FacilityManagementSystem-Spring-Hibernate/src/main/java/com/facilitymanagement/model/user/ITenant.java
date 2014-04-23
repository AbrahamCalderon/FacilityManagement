package com.facilitymanagement.model.user;

public interface ITenant extends IUser {
	void setTenantId(int tenantId);
	int getTenantId();
	void setDob(String bdate);
	String getDob();
	void setSsn(String ssn);
	String getSsn();
	void setSex(Sex sex);
	Sex getSex();
	public void setLeaseId(String leaseId);	
	String getLeaseId();
}
