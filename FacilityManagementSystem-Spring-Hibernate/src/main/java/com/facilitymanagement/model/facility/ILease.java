package com.facilitymanagement.model.facility;

import java.sql.Date;

import com.facilitymanagement.model.user.ITenant;

public interface ILease {
	void setLeaseId(String leaseId);
	String getLeaseId();
	//public void setAptId(int aptId);
	//public int getAptId();
	void setLeaseTerm(LeaseTerm leaseTerm);
	LeaseTerm getLeaseTerm();
	void setRent(double rent);
	double getRent();
	void setMoveInDate(Date moveInDate);
	Date getMoveInDate();
	void setMoveOutDate(Date moveOutDate);
	Date getMoveOutDate();
	void setTenant(ITenant tenant);
	ITenant getTenant();
	/*
	void setUnit(IAptUnit unit);
	IAptUnit getUnit();
	*/
	
}
