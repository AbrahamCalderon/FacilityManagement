package com.facilitymanagement.model.service;

import java.sql.Date;
import java.util.List;

import com.facilitymanagement.dal.AptUnitHibernateDAO;
import com.facilitymanagement.dal.ComplexFacilityHibernateDAO;
import com.facilitymanagement.dal.LeaseHibernateDAO;
import com.facilitymanagement.model.facility.FacilityStatus;
import com.facilitymanagement.model.facility.IAptUnit;
import com.facilitymanagement.model.facility.IComplexFacility;
import com.facilitymanagement.model.facility.ILease;
import com.facilitymanagement.model.facility.IReservation;
import com.facilitymanagement.model.facility.UnitStatus;

public class UsageService {
	
	private LeaseHibernateDAO laDAO = new LeaseHibernateDAO();
	private AptUnitHibernateDAO aptDAO = new AptUnitHibernateDAO();
	private ComplexFacilityHibernateDAO complexDAO = new ComplexFacilityHibernateDAO();
	
	public boolean inUseDuringTimePeriod(IAptUnit unit, Date startDate, Date endDate){
		return overlapsWithExistingReservations(unit.getFutureReservations(), startDate, endDate);
	}
	
	public boolean overlapsWithExistingReservations(List<IReservation> reservations, Date startDate, Date endDate){
		for (IReservation r : reservations){
			int result1 = startDate.compareTo(r.getCheckOutDate());
			int result2 = endDate.compareTo(r.getCheckInDate());
			//System.out.println("result1 = " + result1 + ",\nresult2 = " + result2);
			if(!((result1 > 0) || (result2 < 0))){
				System.out.println("there is overlap");
				return true;
			}
		}
		return false;
	}
	
	public void assignFacilityToUse(IComplexFacility facility, String use){
		
		if(facility.getFacilityStatus().equals(FacilityStatus.ACTIVE))
			System.out.println("Facility already in use.");
		else{
			facility.setFacilityStatus(FacilityStatus.ACTIVE);		
			facility.getDetail().setUsage(use);
			System.out.println("Facility succesfully activated");
			complexDAO.updateComplexFacility(facility);
		}
	}
	
	public void deassignFacilityToUse(IComplexFacility facility){
		facility.setFacilityStatus(FacilityStatus.INACTIVE);		
		//facility.getDetail().setUsages(null);
		facility.getDetail().setUsage(null);
		complexDAO.updateComplexFacility(facility);
	}


	public void rentOutUnit(ILease lease, IAptUnit apt){
		apt.getLeases().add(lease);
		apt.setStatus(UnitStatus.OCCUPIED);
		aptDAO.updateAptUnit(apt);
	}
	
	public void vaccateUnit(IAptUnit unit){
		
		//change status and tenants of unit
		unit.setStatus(UnitStatus.VACANT);		
		aptDAO.updateAptUnit(unit);
		
		//delete leases tied to this unit
		for(ILease l : unit.getLeases())
			laDAO.deleteLease(l);
	}
	
	public void deassignUnitForUse(IAptUnit unit){
		unit.setStatus(UnitStatus.UNAVAILABLE);
		aptDAO.updateAptUnit(unit);
	}
	
	public void listActualUsage(IComplexFacility facility){
		System.out.println("\nFacility Usage: " + facility.getUsage() + "\n");
	}
	
	public double calcUsageRate(IComplexFacility facility){
		double totalUnits = facility.getUnits().size();
		double inUse = 0;
		for(IAptUnit u : facility.getUnits()){
			if(u.getStatus().equals(UnitStatus.OCCUPIED))
				inUse++;
		}
		
		return (inUse/totalUnits) * 100;
	}
	
}
