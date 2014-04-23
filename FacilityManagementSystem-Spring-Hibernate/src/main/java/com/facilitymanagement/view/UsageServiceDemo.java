package com.facilitymanagement.view;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.facilitymanagement.model.facility.IAptUnit;
import com.facilitymanagement.model.facility.IComplexFacility;
import com.facilitymanagement.model.facility.IInspection;
import com.facilitymanagement.model.facility.ILease;
import com.facilitymanagement.model.facility.IReservation;
import com.facilitymanagement.model.facility.LeaseTerm;
import com.facilitymanagement.model.service.FacilityService;
import com.facilitymanagement.model.service.MaintenanceService;
import com.facilitymanagement.model.service.UsageService;
import com.facilitymanagement.model.service.UserService;
import com.facilitymanagement.model.user.ITenant;
import com.facilitymanagement.model.user.Sex;

/*** 
 * CLASS DEMONSTRATES ALL MAJOR FACILITY USAGE SERVICES, INCLUDING:
 * 		- checking if a facility is in use during an interval (two date objects)
 * 		- assigning a facility to use
 * 		- vacating a facility and its units based on ID
 * 		- listing innspections for a given facility based on ID
 * 		- listing actual usages for a given facility based on ID
 * 		- calculating usage rate for a given facility based on ID *
 **/

public class UsageServiceDemo {

	public static void main(String [] args){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/application.xml");
        System.out.println("***************** Application Context instantiated! ******************");

        MaintenanceService maintServ = (MaintenanceService) context.getBean("maintenanceService");
        UsageService usageServ = (UsageService) context.getBean("usageService");
        UserService userServ = (UserService) context.getBean("userService");
	    FacilityService facServ = (FacilityService) context.getBean("facilityService");
        System.out.println("*************** Creating Service objects *************************"); 
	    
        System.out.println("*************** UsageService EXAMPLE *************************");
        
        /**
         * Assign Reservation for unit 
         */
        
        IAptUnit retApt = facServ.getUnitById(2);
        
        IReservation res = (IReservation) context.getBean("reservation");
        java.sql.Date checkIn = java.sql.Date.valueOf("2014-03-15");
        java.sql.Date checkOut = java.sql.Date.valueOf("2014-04-16");
        res.setCheckInDate(checkIn);
        res.setCheckOutDate(checkOut);
        res.setReservationId(1);
        java.sql.Date resDate = java.sql.Date.valueOf("2014-04-15");
        res.setReservationMadeDate(resDate);
        
        
        //book reservation if possible
        facServ.makeReservation(retApt, res);
        
        
        /**
         * Attempt to assign Overlapping Reservation
         * 	- Reservation overlaps with pre-existing reservations for the particular unit.
         *  - Results in UNSUCCESSFUL Reservation
         */
        //obtain apartment first
        IAptUnit apt1 = facServ.getUnitById(2);
        
        IReservation res1 = (IReservation) context.getBean("reservation");
        java.sql.Date checkInDate = java.sql.Date.valueOf("2014-04-20");
        java.sql.Date checkOutDate = java.sql.Date.valueOf("2014-05-10");
        res1.setCheckInDate(checkInDate);
        res1.setCheckOutDate(checkOutDate);
        res1.setReservationId(2);
        java.sql.Date res1Date = java.sql.Date.valueOf("2014-04-30");
        res1.setReservationMadeDate(res1Date);
        
        facServ.makeReservation(apt1, res1);
        
        /**
         * Attempt to assign non-overlapping Reservation
         * 	- Reservation does not overlap with pre-existing reservations for the particular unit
         * 	- Results is SUCCESSFUL Reservation 
         */
        //obtain apartment first
        IAptUnit apt2 = facServ.getUnitById(2);
        
        IReservation res2 = (IReservation) context.getBean("reservation");
        java.sql.Date checkInDate2 = java.sql.Date.valueOf("2014-04-15");
        java.sql.Date checkOutDate2 = java.sql.Date.valueOf("2014-04-23");
        res2.setCheckInDate(checkInDate2);
        res2.setCheckOutDate(checkOutDate2);
        res2.setReservationId(3);
        java.sql.Date res2Date = java.sql.Date.valueOf("2014-04-30");
        res2.setReservationMadeDate(res2Date);
        
        facServ.makeReservation(apt2, res2);
        
        /**
         * Assign Apartmentunit to tenant
         *  - NEW tenant to unit
         */

        System.out.println("\n\n************************* ASSIGN UNIT ID: 2 TO NEW TENANT 1 *************************");
        
        IAptUnit retApt1 = facServ.getUnitById(2);
        
        /*******************************************************/
        /************** TENANT 1 *******************************/
        ITenant tenant1 = (ITenant) context.getBean("tenant");
        tenant1.setTenantId(4);
        tenant1.setDob("08/30/1987");
        tenant1.setLastName("Smith");
        tenant1.setSsn("212222222");
        tenant1.setSex(Sex.MALE);
        tenant1.setFirstName("Mike");

		ILease lease1 = (ILease) context.getBean("lease");
		lease1.setTenant(tenant1);
		lease1.setLeaseId("1000");
		tenant1.setLeaseId(lease1.getLeaseId());
		lease1.setLeaseTerm(LeaseTerm.PER_MONTH);
		
		java.sql.Date moveinDate = java.sql.Date.valueOf("2014-02-15");
		java.sql.Date moveoutDate = java.sql.Date.valueOf("2014-03-30");
		
		lease1.setMoveInDate(moveinDate);
		lease1.setMoveOutDate(moveoutDate);
		lease1.setRent(750.00);
		
		usageServ.rentOutUnit(lease1, retApt1);
		
		System.out.println("\nTENANT 1 ASSIGNED");

        System.out.println("\n************************* ASSIGN UNIT ID: 2 TO NEW TENANT 2 *************************");
        
        IAptUnit retApt2 = facServ.getUnitById(2);
        
		/*****************************************/ 
		/*************** Tenant **************/
		ITenant tenant2 = (ITenant) context.getBean("tenant");
		tenant2.setTenantId(3);
		tenant2.setDob("08/20/1987");
		tenant2.setLastName("Calderon");
		tenant2.setSsn("111111111");
		tenant2.setSex(Sex.MALE);
		tenant2.setFirstName("Daniel");
		
		ILease lease2 = (ILease) context.getBean("lease");
		lease2.setTenant(tenant2);
		//lease.setAptId(au.getUnitId());
		lease2.setLeaseId("999");
		tenant2.setLeaseId(lease2.getLeaseId());
		lease2.setLeaseTerm(LeaseTerm.PER_MONTH);
		
		java.sql.Date moveinDate2 = java.sql.Date.valueOf("2014-02-15");
		java.sql.Date moveoutDate2 = java.sql.Date.valueOf("2014-03-30");
		
		lease2.setMoveInDate(moveinDate2);
		lease2.setMoveOutDate(moveoutDate2);
		lease2.setRent(1400);
		
		usageServ.rentOutUnit(lease2, retApt2);
		
		System.out.println("\nTENANT 2 ASSIGNED");
		
		//VALIDATE TENANTS WERE ADDED -- RETRIEVE APARTMENT UNIT AND DISPLAY ITS TENANTS		
		IAptUnit apt = facServ.getUnitById(2);
		
		System.out.println("UNIT ID: " + apt.getUnitId() + "  ASSIGNED TO:");
		for(ILease l : apt.getLeases()){
			System.out.println(l.getTenant().getUserInfo());
		}
        
        
        /**
         * Vacate Apartmentunit
         *  	- set unit status to 'VACANT'
         *  	- remove any leases associated with unit
         *  	- sets tenants for leases to null
         */
        System.out.println("\n\n************************* VACATE UNIT WITH ID: 2 ****************************");
        usageServ.vaccateUnit(apt);
        System.out.println("***************************** UNIT VACATED SUCCESSFULLY! ************************");


        /**
         * GET FACILITY USAGE
         */
        System.out.println("***************************** GET FACILITY USAGE **********************************");
        IComplexFacility retFac = facServ.getFacilityById(777);
        usageServ.listActualUsage(retFac);
        
        /**
         * GET FACILITY INSPECTIONS
         */
        System.out.println("****************************** GET INSPECTIONS FOR FACILITY *************************");
        List<IInspection> inspections = facServ.getInspectionsForFacilityById(777);
        for(IInspection i : inspections){
        	System.out.println(i);
        }

	}
}
