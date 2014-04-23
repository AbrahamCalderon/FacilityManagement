package com.facilitymanagement.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.facilitymanagement.model.facility.AptUnit;
import com.facilitymanagement.model.facility.AptUnitDetail;
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
import com.facilitymanagement.model.user.Owner;

public class FacilityManagementSpringDemo_Facility {
	
	public static void main(String args[]){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/application.xml");
		System.out.println("********************* Application Context instantiated! **********************");
		
		//Spring injection
		FacilityService facService = (FacilityService)context.getBean("facilityService");
		System.out.println("************* Creating facility service object*********");
		
		
		/*******************************************************************/
		/********************** FACILITY 1 *********************************/
		int facId1 = 8820;
		
		//SIMPLE ATTRIBUTES:
		IComplexFacility facility = (IComplexFacility) context.getBean("complexfacility");
		facility.setFacilityId(facId1);
		facility.setFacilityName("Lakewood Apartments");
		facility.setCity("Yakima");
		facility.setStreet("W. Washington Blvd.");
		facility.setState("WA");
		facility.setZip("98908");		
		facility.setFacilityStatus(FacilityStatus.ACTIVE);
		
		
		//COMPLEX ATTRIBUTES:
		
		/*********************************************/
		/************ INSPECTIONS ********************/	
		Inspection ins = (Inspection)context.getBean("inspection");
		//ins.setFacilityId(facility.getFacilityId());
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
		AptUnitDetail ud = (AptUnitDetail) context.getBean("aptunitdetail");
		ud.setAptDetId(22);
		ud.setAptId(au.getUnitId());
		ud.setLength(22.0);
		ud.setNumBaths(2.5);
		ud.setNumRooms(5);
		ud.setWidth(14.5);
		ud.setMoreDetail("Appliances included!");
		
		au.setDetail(ud);	
		
		
		/********************************************/
		/******* Facility Detail ********************/
		IFacilityDetail facDetail = facility.getDetail();
		facDetail.setFacId(facility.getFacilityId());
		facDetail.setYearBuilt("1993");
		facDetail.setAdditionalDetails("additional info");
		facDetail.setFacDetId(23);
		
		//Usages
		facDetail.setUsage("Residential");
		//List<String> usages = facDetail.getUsages();
		//usages.add("Residential");
		//usages.add("Main Offices");
		
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
		IFeature feat2 = (FacilityFeature) context.getBean("facilityFeature");
		feat2.setFeatureDescription("Rec center");
		features.add(feat);
		features.add(feat2);
		
		
		
		//SET ALL COMPLEX ATTRIBUTES
		List<IInspection> inspections = facility.getInspections();		//set inspections
		inspections.add(ins);			
		List<IAptUnit> units = facility.getUnits();						//set list of units
		units.add(au);
		facility.setDetail(facDetail);									//set detail object
		facility.setFeatures(features);									//set features
		
		/******************************************************************/
		/*************** FACILITY 2 ***************************************		
		int facId2 = 7701;
		
		//SIMPLE ATTRIBUTES:
		IComplexFacility facility2 = (IComplexFacility) context.getBean("complexfacility");
		facility2.setFacilityId(facId2);
		facility2.setFacilityName("Yakima Comfort Apartments");
		facility2.setCity("Yakima");
		facility2.setStreet("12th Ave.");
		facility2.setState("WA");
		facility2.setZip("98908");
				
		facility2.setFacilityStatus(FacilityStatus.ACTIVE);
		
		//COMPLEX ATTRIBUTES:
		/****************************************
		/******* Inspections ********************		
		List<IInspection> inspections2 = facility2.getInspections();
		inspections2.get(0).setFacilityId(facId2);
		inspections2.get(0).setCategory(InspectionType.ELECTRICAL);
		java.sql.Date insDate2 = inspections2.get(0).getDate();
		insDate2 = java.sql.Date.valueOf("2014-05-01");
		inspections2.get(0).setComment("Great condition");
		inspections2.get(0).setInspector("Solaris HVAC Inc.");
		inspections2.get(0).setResult(InspectionResult.PASS);
		
		/**********************************
		/******* Units ********************
		List<IAptUnit> units2 = facility2.getUnits();
		units2.get(0).setFacId(facility2.getFacilityId());
		units2.get(0).setCapacity(6);
		units2.get(0).setStatus(UnitStatus.VACCANT);
		units2.get(0).setUnitId(4);
			
		/********************************************
		/******* Facility Detail ********************
		IFacilityDetail facDetail2 = facility2.getDetail();
		facDetail2.setYearBuilt("1996");
		facDetail2.setAdditionalDetails("additional info");
			
		//Usages
		List<String> usages2 = facDetail2.getUsages();
		usages2.add("Residential");
		usages2.add("Main Offices");
				
		//Owner
		IOwner owner2 = facDetail2.getOwner();
		owner2.setCompany("Infinity Inc.");
		owner2.setFirstName("James");
		owner2.setLastName("Christie");
				
		facDetail2.setOwner(owner2);
		facDetail2.setUsages(usages2);
				
				
		/***********************************************
		/****************** FEATURES *******************
		List<IFeature> features2 = facility2.getFeatures();
		IFeature feat3 = (FacilityFeature) context.getBean("facilityFeature");
		feat3.setFeatureDescription("Free parking");
		IFeature feat4 = (FacilityFeature) context.getBean("facilityFeature");
		feat4.setFeatureDescription("Rec center");
		features2.add(feat3);
		features2.add(feat4);
				
		facility2.setFeatures(features2);
		
		*/
		//---------------------------------------------------------------------------------
		
		System.out.println("************* INSERT EXAMPLE ****************");
		System.out.println("Facility to be inserted: " + facility);
		facService.addNewFacility(facility);		
		System.out.println("********** FACILITY 1 INSERTED ***************");
		//facService.addNewFacility(facility2);
		//System.out.println("********** FACILITY 2 INSERTED ***************");
		
		//---------------------------------------------------------------------------------
		/**
		System.out.println("********* DELETE EXAMPLE ***********************************");
		
		System.out.println("*********** FACILITY TO BE DELETED *********");
		//Find facility if exists
		IComplexFacility facilityToBeDeleted = facService.getFacilityById(facility2.getFacilityId());
		System.out.println(facilityToBeDeleted);		
		facService.removeFacility(facilityToBeDeleted);
		
		//-------------------------------------------------------------------------------
		
		System.out.println("*************** RETRIEVAL EXAMPLE ***************");
		System.out.println("************** RETRIEVING FACILITY ***************");
		
		IComplexFacility facilityToBeRetrieved = facService.getFacilityById(facility.getFacilityId());
		System.out.println(facilityToBeRetrieved);
		
		//-------------------------------------------------------------------------------
		
		System.out.println("***************** UPDATE EXAMPLE *************");
		System.out.println("*********** FACILITY TO BE UPDATED *****************");
		
		IComplexFacility facilityToBeUpdated = facService.getFacilityById(facility.getFacilityId());
		System.out.println(facilityToBeUpdated);
		
		facilityToBeUpdated.setFacilityName("Updated Name");
		facService.modifyFacility(facilityToBeUpdated); //update method in service
		System.out.println("****************** Facility Updated ******************");
		*/
	}
}
