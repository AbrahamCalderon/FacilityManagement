package com.facilitymanagement.view;

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
import com.facilitymanagement.model.user.IOwner;
import com.facilitymanagement.model.facility.IRequest;
import com.facilitymanagement.model.maintenance.IMaintenanceJob;
import com.facilitymanagement.model.maintenance.IMaintenanceLog;
import com.facilitymanagement.model.maintenance.IOrder;
import com.facilitymanagement.model.maintenance.IOrderDetail;
import com.facilitymanagement.model.maintenance.MaintenanceCat;
import com.facilitymanagement.model.maintenance.OrderTerm;
import com.facilitymanagement.model.maintenance.SeverityLevel;
import com.facilitymanagement.model.facility.ILease;
import com.facilitymanagement.model.facility.IReservation;
import com.facilitymanagement.model.facility.LeaseTerm;
import com.facilitymanagement.model.user.ITenant;
import com.facilitymanagement.model.user.Sex;
import java.sql.Date;
import java.util.List;

import com.facilitymanagement.model.service.FacilityService;
import com.facilitymanagement.model.service.MaintenanceService;
import com.facilitymanagement.model.service.UsageService;
import com.facilitymanagement.model.service.UserService;
import com.facilitymanagement.model.service.UtilityService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FacilityManagementSpringHibernateDemo {
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/application.xml");

	public static void main(String[] args){		

		new FacilityManagementSpringHibernateDemo().run();		
	
	}
	
	public void run(){
		
		System.out.println("********************* Application Context instantiated! **********************");
		
		/**
		 * SECTION DEMONSTRATES MAJOR FACILITY SERVICES INCLUDING:
		 * 		- listing all facilities
		 * 		- retrieving facility information based on ID
		 * 		- obtaining capacity for a given facility based on ID
		 * 		- adding/creating a new facility
		 * 		- adding/creating facility details
		 * 		- removing a facility
		 *
		 */
		runFacilityServiceDemo();
		
		/**
		 * CLASS DEMONSTRATES ALL MAJOR MAINTENANCE SERVICES, INCLUDING:
		 * 		- Making facility maintenance request
		 * 		- Schedule maintenance
		 * 		- Calculating maintenance cost for a given facility (given its ID)
		 * 		- Calculate problem reate for a facility (given its ID)
		 * 		- Calculate down time for a facility (given its ID)
		 * 		- Listing all maintenance request for a given facility (given its ID)
		 * 		- List maintenance (routine maintenace) for a given facility via its ID
		 * 		- List all facility problems for a given facility
		 **/
		runMaintenanceServiceDemo();
		
		/*** 
		 * CLASS DEMONSTRATES ALL MAJOR FACILITY USAGE SERVICES, INCLUDING:
		 * 		- checking if a facility is in use during an interval (two date objects)
		 * 		- assigning a facility to use
		 * 		- vacating a facility and its units based on ID
		 * 		- listing innspections for a given facility based on ID
		 * 		- listing actual usages for a given facility based on ID
		 * 		- calculating usage rate for a given facility based on ID
		 *
		 **/
		runUsageServiceDemo();
		
		/**
		 * Clear Database
		 */
		
		clearDatabase();
	}
	
	
	//FACILITY SERVICE DEMO
	public void runFacilityServiceDemo(){
		
		System.out.println("\n\n\n++++RUNNING FACILITY SERVICE DEMO...+++++\n\n\n");
		
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
			
		//INSERT INTO DB VIA HIBERNATE
		System.out.println("\n\n********************* ADDING A NEW FACILITY ********************");
		facServ.addNewFacility(facility);
		System.out.println("********************* COMPLETED FACILITY INSERTION *********************\n");
		
		/**
		 * LIST ALL FACILITIES
		 */
		System.out.println("\n\n************ LIST ALL FACILITIES CURRENTLY IN SYSTEM *******************");
		facServ.listAllFacilities();
				
		/**
		 * GET FACILILTY INFO
		 */
		IComplexFacility retFac = facServ.getFacilityById(777);
		System.out.println("\n\n************ GET FACILITY INFORMATION FOR FACILITY 777 *****************");
		retFac.getFacilityInformation();
		
		/**
		 * GET CAPACITY FOR FACILITY
		 */
		System.out.println("\n\n************ CAPACITY FOR FACILITY 777 *****************");
		facServ.getFacilityCapacity(retFac);
	}
	
	
	//MAINTENACE SERVICE DEMO
	public void runMaintenanceServiceDemo(){
		
		System.out.println("\n\n\n+++++++++++++++++++RUNNING MAINTENANCE SERVICE DEMO...+++++++\n\n\n");
		
		MaintenanceService maintServ = (MaintenanceService) context.getBean("maintenanceService");
        UserService userServ = (UserService) context.getBean("userService");
	    FacilityService facServ = (FacilityService) context.getBean("facilityService");
	    
        System.out.println("\n\n*************** MaintenanceRequest EXAMPLE *************************");
 
		/**
		 * STEP 1: TENANT or MANAGMENT submits a REQUEST
		 *  - request object is instantiated with a default status of "PENDING"
		 */
        
        //1.1 - RETRIEVE A FACILITY TO MAKE A REQUEST FOR
        
        IComplexFacility retFac = facServ.getFacilityById(777);
        
        
        List<IAptUnit> units = retFac.getUnits();
        IAptUnit retApt = (IAptUnit)context.getBean("apartmentunit");
        //Select a unit to make a request for - unit with id: 2
        for(IAptUnit a : units){
        	if(a.getUnitId() == 2)
        		retApt = a;
        }
        
        //IAptUnit retApt = facServ.getUnitById(2);

        //1.2 - CREATE REQUEST
		java.sql.Time reqTime = java.sql.Time.valueOf( "18:05:00" );
		Date reqDate = Date.valueOf("2014-02-01");
		String probDescription = "Light switch seems to not be working properly";        
        IRequest req = (IRequest) context.getBean("request");
		req.setFacility(retFac);
		req.setRequestId(1);
		req.setUnit(retApt);
		req.setTimeRequested(reqTime);
		req.setDateRequested(reqDate);
		req.setCategory(MaintenanceCat.ELECTRICAL);
		req.setDescription(probDescription);
		req.setRequester("Scott");
		
		/**
		 * 1.3  MAKE FACILITY MAINTENANCE REQUEST
		 * 		  -set default value status to 'PENDING'
		 */
		maintServ.makeMaintenanceRequest(req);
		
		
		/**
		 * STEP 2: LIST ALL MAINTENANCE REQUESTS
		 * 			- managment will decide to schedule/order a maintenance job
		 */

		List<IRequest> reqs = maintServ.listMaintRequestsForFacility(retFac);
		System.out.println("\n\n********************************** All Requests For Facility *****************************");
		int numOfReq = reqs.size();
		System.out.println("\n\nTotal Number of Requests: " + numOfReq + "\n");
		for(IRequest r : maintServ.listMaintRequestsForFacility(retFac))
			System.out.println(r);
		System.out.println("\n\n*********************************** end All Requests **************************************\n\n");
		
		/**
		 *  STEP 3: Management places necessary order for maintenance-order
		 *              AND schedules maintenance
		 *              a) create order detail - set maint. job to the detail
		 *              	- STATUS OF ORDER = SCHEDULED
		 *              b) add order detail to the ORDER's List<orderDetail>
		 *              c) set the ORDER's details as List<orderDetail>
		 *              d.) As result request object is updated with a status of "PROCESSING"
		 */

		//3.1 - CREATE an order for the maintenance
		IOrder mOrder = (IOrder) context.getBean("maintenanceOrder");
		mOrder.setOrderId(1002);
		
		java.util.Date today = new java.util.Date();
		long t = today.getTime();
		java.sql.Date orderDate = new java.sql.Date(t);
		
		mOrder.setOrderDate(orderDate);
		
		//Get Order's details
		List<IOrderDetail> orderItems = mOrder.getOrderDetails();
		
		//3.2 - associate orderDetail(s) with Order
		IOrderDetail oDetail = (IOrderDetail) context.getBean("orderDetail");
		oDetail.setTerm(OrderTerm.SINGLE);
		oDetail.setQuantity(1);
		oDetail.setOrderDetailId(1);
		
		
		//3.3 - associate job(s) with orderDetail(s)
		IMaintenanceJob job = oDetail.getJob();
		job.setDetailId(oDetail.getOrderDetailId());
		job.setJobPerformer("STS Electric Co.");
		job.setPrice(1200); //taxRate and total will be calculated
		job.setJobId(1);
		job.setJobDescription("Circuit breakers need to be replaced and main entrance hallway re-wired.");
		
		//add orderDetail to Order
		orderItems.add(oDetail);		
		
		
		//3.4 	- set request object for Order
		IRequest retReq = maintServ.getRequestById(1);
		mOrder.setRequest(retReq);
		
		//3.5 - create schedule record for log
		IMaintenanceLog log = (IMaintenanceLog) context.getBean("maintenanceLog");
		log.setLogId(1);
		log.setOrder(mOrder);
		log.setSeverityLevel(SeverityLevel.MINOR); //decide job severity
	
		
		/**
		 * 3.6 - SCHEDULE MAINTENANCE
		 */
		//process first order
		maintServ.processOrder(log);
		System.out.println("MAINTENANCE SCHEDULED!\n\n");

		// Display recently added order details
		IMaintenanceLog retLogRec = maintServ.getMaintenanceLog(log.getLogId());
		IOrder retOrder = maintServ.getOrderById(retLogRec.getOrder().getOrderId());
		System.out.println("\nMAINTENANCE SCHEDULED DETAILS:");
		System.out.println("Order ID: " + retOrder.getOrderId() + "\tDate: " + retOrder.getOrderDate().toString());

		System.out.println("Items:");
		for(IOrderDetail det : retOrder.getOrderDetails()){
			System.out.println("\t" + det.getJob().getJobPerformer());
		}
		System.out.println("\nTotal:\t$" + retOrder.getTotalCost());

		
		/**********************************************************************
		 * MaintenanceRequest #2 for demo purposes
		 **********************************************************************/
        //1.2 - CREATE REQUEST
		java.sql.Time reqTime1 = java.sql.Time.valueOf( "18:05:00" );
		Date reqDate1 = Date.valueOf("2014-02-30");
		String probDescription1 = "Facility groundkeeping";        
        IRequest req1 = (IRequest) context.getBean("request");
		req1.setFacility(retFac);
		req1.setRequestId(2);
		req1.setUnit(null); //no aptUnit for facility ONLY-maintenance
		req1.setTimeRequested(reqTime1);
		req1.setDateRequested(reqDate1);
		req1.setCategory(MaintenanceCat.GROUNDKEEPING);
		req1.setDescription(probDescription1);
		req1.setRequester("Management");

		//save request
		maintServ.makeMaintenanceRequest(req1);
		
		//Create a second Order
		IOrder mOrder1 = (IOrder) context.getBean("maintenanceOrder");
		mOrder1.setOrderId(5110);
		
		java.util.Date today1 = new java.util.Date();
		long ti = today1.getTime();
		java.sql.Date orderDate1 = new java.sql.Date(ti);
		
		mOrder1.setOrderDate(orderDate1);
		
		List<IOrderDetail> orderItems1 = mOrder1.getOrderDetails();
		
		IOrderDetail oDetail1 = (IOrderDetail) context.getBean("orderDetail");
		oDetail1.setTerm(OrderTerm.MONTHLY);
		oDetail1.setQuantity(1);
		oDetail1.setOrderDetailId(2);
		//taxRate and total should be calc. for you
		

		IMaintenanceJob job1 = oDetail1.getJob();
		job1.setDetailId(oDetail1.getOrderDetailId());
		job1.setJobPerformer("Outdoor Co.");
		job1.setPrice(500.00);
		job1.setJobId(2);
		job1.setJobDescription("Groundkeeping");
		
		orderItems1.add(oDetail1);
		
		IRequest retReq1 = maintServ.getRequestById(2);
		mOrder1.setRequest(retReq1);

		IMaintenanceLog log1 = (IMaintenanceLog) context.getBean("maintenanceLog");
		log1.setLogId(2);
		log1.setOrder(mOrder1);
		log1.setSeverityLevel(SeverityLevel.MINOR); //decide job severity
		
		//process second order
		maintServ.processOrder(log1);
		
		/************************ END of Maintenance #2 *************************/
		
		
		
		/************************* MANAGEMENT TASKS BELOW *****************************
		 *  listMaintenance()
		 *  calculateMaintenanceCostForFacility()
		 *  calculateDownTimeForFacility()
		 *  calculateProblemRateForFacility()
		 *  listFacilityProblems()  		
		/*****************************************************************************/
		
		IComplexFacility retFacility = facServ.getFacilityById(777);
		
		/**
		 * LIST Maintenance (all routine maintenance)
		 */
		List<IMaintenanceJob> jobs = maintServ.listRoutineMaintenanceForFacility(retFacility);
		System.out.println("\n\n****************** LIST ALL MAINTENANCE (ROUTINE-JOBS) FOR FACILITY 777 ***************\n\n");
		int idx = 0;
		for(IMaintenanceJob j : jobs){
			idx++;
			System.out.println("Maintenance Job [" + idx + "]\n" + j);
		}
		
		/**
		 * CALCULATE ALL MAINTENANCE COST(s) FOR FACILITY
		 */
		double totCost = maintServ.calculateMaintenanceCostForFacility(retFac);
		System.out.println("\n\n*********************** TOTAL MAINT. COST FOR FACILITY" + retFac.getFacilityId() +" **********************");
		System.out.println("Total: \t$" + totCost);
		
		/**
		 * COMPLETE MAINTENANCE 
		 */
		IMaintenanceLog retLog = maintServ.getMaintenanceLog(1);
		IOrder o = maintServ.getOrderById(retLog.getOrder().getOrderId());
		retLog.setOrder(o);
		retLog.getMaintCat();
		retLog.getSeverityLevel();
		java.sql.Date completionDate = java.sql.Date.valueOf("2014-04-16");
		int duration = 45;
		maintServ.completJob(retLog, completionDate, duration);
		
		/**
		 * CALCULATE DOWN TIME FOR FACILITY
		 * 		- calculate any maintenance that was considered 'SEVERE' - this implies down time resulted 
		 */	
		double downTime = maintServ.calcDownTimeForFacility(retFacility);
		System.out.println("\n\n*********************** TOTAL DOWNTIME FOR FACILITY " + retFacility.getFacilityId() + "**************************");
		System.out.println("Facility Down Time (min): " + downTime);
		
		
		/**
		 * LIST FACILITY PROBLEMS
		 */
		List<String> problems = maintServ.retrieveMajorIssuesForFacilityById(777);
		System.out.println("\n\n************************ LIST FACILITY PROBLEMS FOR FACILITY " + retFacility.getFacilityId() + "***********************");
		
		for(String s : problems){
			System.out.println(s);
		}
		
		/**
		 * CALCULATE PROBLEM RATE FOR FACILITY
		 */
		System.out.println("\n\n************************* FACILITY PROBLEM RATE FOR FACILITY ID 777 ************************");
		System.out.println(maintServ.getProblemRateForFacilityById(777) + "%");
		
		/**
		 * CALCULATE USAGE RATE FOR FACILITY
		 */
		System.out.println("\n\n************************* FACILITY USAGE RATE FOR FACILITY ID 777 ************************");
		System.out.println(maintServ.getUsageRateForFacility(777) + "%");
	}
	
	
	//USAGE SERVICE DEMO
	public void runUsageServiceDemo(){
		
		System.out.println("\n\n\n++++++++++++++++RUNNING USAGE SERVICE DEMO...+++++\n\n\n");
		
		MaintenanceService maintServ = (MaintenanceService) context.getBean("maintenanceService");
        UsageService usageServ = (UsageService) context.getBean("usageService");
        UserService userServ = (UserService) context.getBean("userService");
	    FacilityService facServ = (FacilityService) context.getBean("facilityService");
	    
        System.out.println("\n*************** UsageService EXAMPLE *************************");
        
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
        
        System.out.println("\n\n************************* Attempting to place initial reservation ***************************");
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
        
        System.out.println("\n\n ********************************* Attempting to place a non-overlapping reservation (Good) ********************************");
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
        
        System.out.println("\n\n *************************** Attempting to place an overlapping reservation (BAD) *****************************");
        facServ.makeReservation(apt2, res2);
        
        /**
         * Assign Apartmentunit to tenant
         *  - NEW tenant to unit
         */

        System.out.println("\n\n************************* ASSIGNING UNIT ID: 2 TO NEW TENANT 1 *************************");
        
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
		lease1.setLeaseTerm(LeaseTerm.PER_WEEK);
		
		java.sql.Date moveinDate = java.sql.Date.valueOf("2014-02-15");
		java.sql.Date moveoutDate = java.sql.Date.valueOf("2014-03-30");
		
		lease1.setMoveInDate(moveinDate);
		lease1.setMoveOutDate(moveoutDate);
		lease1.setRent(750.00);
		
		usageServ.rentOutUnit(lease1, retApt1);
		
		System.out.println("\n\nSUCCESSFULLY ASSIGNED TENANT 1");

        System.out.println("\n\n************************* ASSIGNING UNIT ID: 2 TO NEW TENANT 2 *************************");
        
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
		lease2.setLeaseId("999");
		tenant2.setLeaseId(lease2.getLeaseId());
		lease2.setLeaseTerm(LeaseTerm.PER_MONTH);
		
		java.sql.Date moveinDate2 = java.sql.Date.valueOf("2014-02-15");
		java.sql.Date moveoutDate2 = java.sql.Date.valueOf("2014-03-30");
		
		lease2.setMoveInDate(moveinDate2);
		lease2.setMoveOutDate(moveoutDate2);
		lease2.setRent(1400);
		
		usageServ.rentOutUnit(lease2, retApt2);
		
		System.out.println("\n SUCCESSFULLY ASSIGNED TENANT 2");
		
		//VALIDATE TENANTS WERE ADDED -- RETRIEVE APARTMENT UNIT AND DISPLAY ITS TENANTS		
		System.out.println("\n\nRe-retrieving apartmentunit after assigning tenants..");		
		IAptUnit apt = facServ.getUnitById(2);
		
		System.out.println("\nUNIT ID: " + apt.getUnitId() + "  CURRENT TENANT(S):");
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
        System.out.println("\n\n***************************** GET FACILITY USAGE **********************************\n");
        IComplexFacility retFac = facServ.getFacilityById(777);
        usageServ.listActualUsage(retFac);
        
        /**
         * GET FACILITY INSPECTIONS
         */
        System.out.println("****************************** GET INSPECTIONS FOR FACILITY *************************");
        List<IInspection> inspections = facServ.getInspectionsForFacilityById(777);
        System.out.println();
        for(IInspection i : inspections){
        	System.out.println(i + "\n");
        }

	}
	
	//Clear databse -- ran after every run
	public void clearDatabase(){
		
		UtilityService utilDAO = (UtilityService)context.getBean("utilityService");
		
		System.out.println("\n\n**************************** REMOVING ALL RECORDS UPON COMPLETION ************************\n");
		utilDAO.removeAllRecords();
		System.out.println("\n\n**************************** SUCCESSFULLY REMOVED ALL RECORDS ****************************\n");
	}
	
}
