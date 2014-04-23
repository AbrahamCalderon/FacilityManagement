package com.facilitymanagement.model.service;

import java.util.List;
import com.facilitymanagement.dal.AptUnitHibernateDAO;
import com.facilitymanagement.dal.ComplexFacilityHibernateDAO;
import com.facilitymanagement.dal.ReservationHibernateDAO;
import com.facilitymanagement.model.facility.ComplexFacility;
import com.facilitymanagement.model.facility.FacilityStatus;
import com.facilitymanagement.model.facility.IAptUnit;
import com.facilitymanagement.model.facility.IComplexFacility;
import com.facilitymanagement.model.facility.IFacility;
import com.facilitymanagement.model.facility.IInspection;
import com.facilitymanagement.model.facility.IReservation;

public class FacilityService {
	
	private ComplexFacilityHibernateDAO cfDAO = new ComplexFacilityHibernateDAO();
	private AptUnitHibernateDAO aptDAO = new AptUnitHibernateDAO();
	private ReservationHibernateDAO resDAO = new ReservationHibernateDAO();

	public void listAllFacilities(){
		int i = 0;
		for(IFacility fac : cfDAO.getAllComplexFacilities()){
			i++;
			System.out.println("*********************** ALL FACILITIES RECORDED *************");
			System.out.println("\n" + i + ".\t" + fac.getFacilityName() + "  [" + fac.getFacilityId() + "]\n\n");
		}
	}
	
	public void addNewFacility(IComplexFacility fac1){
		cfDAO.insertNewComplexFacility(fac1);
	}
	
	public void addFacilityDetail(ComplexFacility facility, String detail){
		facility.addDetail(detail);
		cfDAO.updateComplexFacility(facility);
	}
	
	public void removeFacility(IComplexFacility facility){
		//facility.setFacilityStatus(FacilityStatus.INACTIVE);
		//modifyFacility(facility);
		cfDAO.deleteComplexFacility(facility);
	}
	
	public void modifyFacility(IComplexFacility facility){
		//change to inactive
		cfDAO.updateComplexFacility(facility);
	}
	
	public List<IInspection> getInspectionsForFacilityById(int facId){
		IComplexFacility retFac = getFacilityById(facId);
		return retFac.getInspections();
	}
	
	public IComplexFacility getFacilityById(int fid){
		return cfDAO.getComplexFacilityById(fid);
	}
	
	public IAptUnit getUnitById(int id){
		return aptDAO.getAptUnitById(id);
	}
	
	public void updateAptUnit(IAptUnit apt){
		aptDAO.updateAptUnit(apt);
	}
	
	public void makeReservation(IAptUnit apt, IReservation res){
		UsageService useServ = new UsageService();
		if(!(useServ.inUseDuringTimePeriod(apt, res.getCheckInDate(), res.getCheckOutDate()))){
			//resDAO.insertReservation(res);
			apt.addReservation(res);
			aptDAO.updateAptUnit(apt);
			System.out.println("\nRESERVATION SUCCESFFULLY MADE!\n\n");
		}
		else
			System.out.println("\nCOULDN'T MAKE RESERVATIONS!\n\n");
	}
	
	//display a given facility's info
	public void getFacilityInfo(IFacility facility){
		facility.getFacilityInformation();
	}
	
	//request availability capacity
	public void getFacilityCapacity(IFacility facility){
		System.out.println("\nMaximum Facility Capacity: " + facility.requestAvailableCapacity());
	}
	
}
