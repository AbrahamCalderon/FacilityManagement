package com.facilitymanagement.model.facility;

import java.sql.Date;
import com.facilitymanagement.model.user.ITenant;

public class Lease implements ILease{
	
	private String leaseId;
	//private int aptId;
	private LeaseTerm leaseTerm = LeaseTerm.PER_MONTH;
	private double rent;
	private Date moveInDate;
	private Date moveOutDate;
	private ITenant tenant;
	
	public Lease(){}
	
	public Lease(LeaseTerm leaseTerm, double rent, Date moveInDate, Date moveOutDate, ITenant tenant){
		this.leaseTerm = leaseTerm;
		this.rent = rent;
		this.moveInDate = moveInDate;
		this.moveOutDate = moveOutDate;
		this.tenant = tenant;
	}
	
	public void setLeaseId(String leaseId){
		this.leaseId = leaseId;
	}
	public String getLeaseId(){
		return leaseId;
	}
	/*
	public void setAptId(int aptId){
		this.aptId = aptId;
	}
	public int getAptId(){
		return aptId;
	}
	*/
	public void setLeaseTerm(LeaseTerm leaseTerm){
		this.leaseTerm = leaseTerm;
	}
	public LeaseTerm getLeaseTerm(){
		return leaseTerm;
	}
	public void setRent(double rent){
		this.rent = rent;
	}
	public double getRent(){
		return rent;
	}
	public void setMoveInDate(Date moveInDate){
		this.moveInDate = moveInDate;
	}
	public Date getMoveInDate(){
		return moveInDate;
	}
	public void setMoveOutDate(Date moveOutDate){
		this.moveOutDate = moveOutDate;
	}
	public Date getMoveOutDate(){
		return moveOutDate;
	}
	public void setTenant(ITenant tenant){
		this.tenant = tenant;
	}
	public ITenant getTenant(){
		return tenant;
	}
	
	/*
	public void setUnit(IAptUnit unit){
		this.unit = unit;
	}
	public IAptUnit getUnit(){
		return unit;
	}
	*/
	
	public String displayTenant(){
		return tenant.getFirstName() + " " + tenant.getLastName();
	}
	
	@Override
	public String toString(){
		return "LEASING AGREEMENT\n\n" + "\nLease: $" + rent + "/"  + leaseTerm + 
				"\nMove-in Date: " + moveInDate + "\nMove-out Date: " + moveOutDate +
				"Renter(s): \n" + this.displayTenant();
	}

}
