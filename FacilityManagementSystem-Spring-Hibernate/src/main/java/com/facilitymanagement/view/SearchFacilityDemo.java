package com.facilitymanagement.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.facilitymanagement.model.facility.IAptUnit;
import com.facilitymanagement.model.facility.IComplexFacility;
import com.facilitymanagement.model.facility.IFacility;
import com.facilitymanagement.model.facility.IFacilityDetail;
import com.facilitymanagement.model.facility.IInspection;
import com.facilitymanagement.model.service.FacilityService;

public class SearchFacilityDemo {
	public static void main (String args[]) throws Exception {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/application.xml");
        System.out.println("***************** Application Context instantiated! ******************");

        FacilityService facServ = (FacilityService) context.getBean("facilityService");
	    System.out.println("*************** Creating Facility Service object *************************"); 
	    
        System.out.println("*************** SEARCH EXAMPLE *************************");
 
        //Find a facility if already exists; if not, create a new one.
        IComplexFacility retFac = facServ.getFacilityById(777); 
        IFacilityDetail facDet = retFac.getDetail();
        System.out.println("Searched facility information .......>>\n");
        
        System.out.println("******* Details for Facility ID ... " + retFac.getFacilityId() + "*********");
        retFac.getFacilityInformation();
        //System.out.println(retFac.getDetail().getDetails());
        System.out.println("Status: " + retFac.getFacilityStatus().toString()); 
        System.out.println("Year Built: " + facDet.getYearBuilt());
        System.out.println("Additional Info: " + facDet.getAdditionalDetails());
        System.out.println("Owner: " + retFac.getOwner().getUserInfo());
        System.out.println("Usages: " + retFac.getUsage());
        System.out.println("Number of units: " + retFac.getUnits().size());
        System.out.println("Number of Inspections: " + retFac.getInspections().size());
        for(IInspection i :retFac.getInspections()){
        	System.out.println(" - " + i.getCategory().toString());
        }
        //retFac.getFacilityInformation();
	}
}
