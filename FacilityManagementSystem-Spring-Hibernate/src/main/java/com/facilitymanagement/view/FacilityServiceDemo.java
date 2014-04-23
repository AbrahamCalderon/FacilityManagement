package com.facilitymanagement.view;

/**
 * CLASS DEMONSTRATES MAJOR FACILITY SERVICES INCLUDING:
 * 		- listing all facilities
 * 		- retrieving facility information based on ID
 * 		- obtaining capacity for a given facility based on ID
 * 		- adding/creating a new facility
 * 		- adding/creating facility details
 * 		- removing a facility
 *
 */

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.facilitymanagement.model.facility.AptUnit;
import com.facilitymanagement.model.facility.FacilityFeature;
import com.facilitymanagement.model.facility.FacilityStatus;
import com.facilitymanagement.model.facility.IAptUnit;
import com.facilitymanagement.model.facility.IComplexFacility;
import com.facilitymanagement.model.facility.IFacilityDetail;
import com.facilitymanagement.model.facility.IFeature;
import com.facilitymanagement.model.facility.IInspection;
import com.facilitymanagement.model.facility.IUnitDetail;
import com.facilitymanagement.model.facility.Inspection;
import com.facilitymanagement.model.facility.InspectionResult;
import com.facilitymanagement.model.facility.InspectionType;
import com.facilitymanagement.model.facility.UnitStatus;
import com.facilitymanagement.model.service.FacilityService;
import com.facilitymanagement.model.user.IOwner;



public class FacilityServiceDemo {

	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/application.xml");
		System.out.println("********************* Application Context instantiated! **********************");
		
		//Spring injection
		FacilityService facServ = (FacilityService)context.getBean("facilityService");
		System.out.println("************* Creating FACILITY SERVICE object*********");
		
		/***********************/
		/** Create facility
		/***********************/
		IComplexFacility facility = (IComplexFacility) context.getBean("complexfacility");
		facility.setFacilityId(777);
		facility.setFacilityName("Lakewood Apartments");
		facility.setCity("Yakima");
		facility.setStreet("W. Washington Blvd.");
		facility.setState("WA");
		facility.setZip("98908");		
		facility.setFacilityStatus(FacilityStatus.ACTIVE);
		
		/********************************************/
		/******* Facility Detail ********************/
		IFacilityDetail facDetail = facility.getDetail();
		facDetail.setFacId(facility.getFacilityId());
		facDetail.setYearBuilt("1993");
		facDetail.setAdditionalDetails("additional info");
		facDetail.setFacDetId(1);
		
		//Usages
		facDetail.setUsage("Residential");
		
		//Owner
		IOwner owner = facility.getOwner();
		owner.setFacilityId(facility.getFacilityId());
		owner.setOwnerId(22);
		owner.setCompany("Infinity Inc.");
		owner.setFirstName("James");
		owner.setLastName("Christie");
		
		/***********************************************/
		/****************** FEATURES *******************/
		List<IFeature> features = facility.getFeatures();
		
		IFeature feat = (FacilityFeature) context.getBean("facilityFeature");
		feat.setFeatureDescription("Free parking");
		feat.setFeatureId(11);
		
		IFeature feat2 = (FacilityFeature) context.getBean("facilityFeature");
		feat2.setFeatureDescription("Rec center");
		feat2.setFeatureId(12);
		
		features.add(feat);
		features.add(feat2);
		
		/*********************************************/
		/************ INSPECTIONS ********************/	
		Inspection ins = (Inspection)context.getBean("inspection");
		ins.setInspectionId(1);	
		ins.setCategory(InspectionType.AIR_CONDITIONING);		
		ins.setComment("Needs immediate work!");
		ins.setInspector("Solaris HVAC Inc.");
		ins.setResult(InspectionResult.FAIL);
		
		java.sql.Date insDate = java.sql.Date.valueOf("2014-05-01");
		ins.setDate(insDate);	
		
	
		/***************************************/
		/********** UNIT(s) ********************/
		AptUnit au = (AptUnit)context.getBean("apartmentunit");
		au.setFacId(facility.getFacilityId());
		au.setCapacity(6);
		au.setStatus(UnitStatus.VACANT);
		au.setUnitId(2);		

		
		/*******************************************/
		/*************** Unit Detail ***************/
		IUnitDetail ud = au.getDetail();
		ud.setAptDetId(22);
		ud.setAptId(au.getUnitId());
		ud.setLength(22.0);
		ud.setNumBaths(2.5);
		ud.setNumRooms(5);
		ud.setWidth(14.5);
		ud.setMoreDetail("Appliances included!");
		
		/*****************************************/ 
		/*************** Unit Feature ************/
		IFeature fet = (IFeature) context.getBean("unitFeature");
		fet.setFeatureDescription("Includes appliances");
		fet.setFeatureId(52233);
		
			
		/********SET ALL COMPLEX ATTRIBUTES*******/
		
		List<IInspection> inspections = facility.getInspections();		//set inspections
		inspections.add(ins);
		
		List<IAptUnit> units = facility.getUnits();						//set list of units
		units.add(au);
		
		List<IFeature> au_fet = au.getFeatures();						//set unit features
		au_fet.add(fet);
		
		/**
		List<ILease> leases = au.getLeases();							//set unit Lease
		leases.add(lease);
		**/
		
		//INSERT INTO DB VIA HIBERNATE
		System.out.println("********************* ADDING A NEW FACILITY ********************");
		facServ.addNewFacility(facility);
		System.out.println("********************* COMPLETED FACILITY INSERTION *********************\n");
		
		/**
		 * LIST ALL FACILITIES
		 */
		System.out.println("\n************ LIST ALL FACILITIES CURRENTLY IN SYSTEM *******************");
		facServ.listAllFacilities();
		
		/**
		 * GET FACILILTY INFO
		 */
		IComplexFacility retFac = facServ.getFacilityById(777);
		System.out.println("\n************ GET FACILITY INFORMATION FOR FACILITY 777 *****************");
		retFac.getFacilityInformation();
		
		/**
		 * GET CAPACITY FOR FACILITY
		 */
		System.out.println("\n************ CAPACITY FOR FACILITY 777 *****************");
		facServ.getFacilityCapacity(retFac);
		
		/**
		 * REMOVE/DELETE FACILITY
		 */
		//System.out.println("\n************* DELETING FACILITY 777 ********************");
		//facServ.removeFacility(retFac);
		
	}
}
