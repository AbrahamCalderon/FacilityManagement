package com.facilitymanagement.view;

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

import java.sql.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.facilitymanagement.model.facility.AptUnit;
import com.facilitymanagement.model.facility.IAptUnit;
import com.facilitymanagement.model.facility.IComplexFacility;
import com.facilitymanagement.model.facility.IRequest;
import com.facilitymanagement.model.maintenance.IMaintenanceJob;
import com.facilitymanagement.model.maintenance.IMaintenanceLog;
import com.facilitymanagement.model.maintenance.IOrder;
import com.facilitymanagement.model.maintenance.IOrderDetail;
import com.facilitymanagement.model.maintenance.MaintenanceCat;
import com.facilitymanagement.model.maintenance.OrderTerm;
import com.facilitymanagement.model.maintenance.SeverityLevel;
import com.facilitymanagement.model.service.FacilityService;
import com.facilitymanagement.model.service.MaintenanceService;
import com.facilitymanagement.model.service.UserService;

public class MaintenanceServiceDemo {
	
	public static void main (String args[]) throws Exception {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/application.xml");
        System.out.println("***************** Application Context instantiated! ******************");

        MaintenanceService maintServ = (MaintenanceService) context.getBean("maintenanceService");
        UserService userServ = (UserService) context.getBean("userService");
	    FacilityService facServ = (FacilityService) context.getBean("facilityService");
        System.out.println("*************** Creating Service objects *************************"); 
	    
        System.out.println("*************** MaintenanceRequest EXAMPLE *************************");
 
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
		System.out.println("\n\n=======================All Requests For Facility =====>");
		for(IRequest r : maintServ.listMaintRequestsForFacility(retFac))
			System.out.println(r);
		System.out.println("<===========end All Requests================\n\n");
		
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
		//taxRate and total should be calc. for you
		
		//3.3 - associate job(s) with orderDetail(s)
		IMaintenanceJob job = oDetail.getJob();
		job.setDetailId(oDetail.getOrderDetailId());
		job.setJobPerformer("STS Electric Co.");
		job.setPrice(1200.00);
		job.setJobId(1);
		job.setJobDescription("Circuit breakers need to be replaced and main entrance hallway re-wired.");
		
		//add orderDetail to Order
		orderItems.add(oDetail);
		
		//3.4 - set request object for Order
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
		maintServ.processOrder(log);
		//*
		System.out.println("MAINTENANCE SCHEDULED!\n");
		
		IOrder retOrder = maintServ.getOrderById(log.getOrder().getOrderId());
		System.out.println("\nMAINTENANCE SCHEDULED DETAILS:");
		System.out.println("Order ID: " + retOrder.getOrderId() + "\tDate: " + retOrder.getOrderDate().toString());

		System.out.println("Items:");
		for(IOrderDetail det : retOrder.getOrderDetails()){
			System.out.println("\t" + det.getJob().getJobPerformer());
		}
		System.out.println("\nTotal:\t$" + retOrder.getTotalCost());
		/*/

		
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
		//IAptUnit dummyApt = (IAptUnit) context.getBean("apartmentunit");
		req1.setUnit(null);
		req1.setTimeRequested(reqTime1);
		req1.setDateRequested(reqDate1);
		req1.setCategory(MaintenanceCat.GROUNDKEEPING);
		req1.setDescription(probDescription1);
		req1.setRequester("Management");

		//save request
		maintServ.makeMaintenanceRequest(req1);
		
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
		List<IMaintenanceJob> jobs = maintServ.listRoutineMaintenanceForFacility(retFac);
		System.out.println("****************** LIST ALL MAINTENANCE (ROUTINE-JOBS) FOR FACILITY 777 ***************");
		for(IMaintenanceJob j : jobs){
			System.out.println(j);
		}
		
		/**
		 * CALCULATE ALL MAINTENANCE COST(s) FOR FACILITY
		 */
		double totCost = maintServ.calculateMaintenanceCostForFacility(retFac);
		System.out.println("*********************** TOTAL MAINT. COST FOR FACILITY" + retFac.getFacilityId() +" **********************");
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
		System.out.println("*********************** TOTAL DOWNTIME FOR FACILITY " + retFacility.getFacilityId() + "**************************");
		System.out.println("Facility Down Time (min): " + downTime);
		
		
		/**
		 * LIST FACILITY PROBLEMS
		 */
		List<String> problems = maintServ.retrieveMajorIssuesForFacilityById(777);
		System.out.println("\n************************ LIST FACILITY PROBLEMS FOR FACILITY " + retFacility.getFacilityId() + "***********************");
		
		for(String s : problems){
			System.out.println(s);
		}
		
		/**
		 * CALCULATE PROBLEM RATE FOR FACILITY
		 */
		System.out.println("\n************************* FACILITY PROBLEM RATE FOR FACILITY ID 777 ************************");
		System.out.println(maintServ.getProblemRateForFacilityById(777) + "%");
		
		/**
		 * CALCULATE USAGE RATE FOR FACILITY
		 */
		System.out.println("\n************************* FACILITY USAGE RATE FOR FACILITY ID 777 ************************");
		System.out.println(maintServ.getUsageRateForFacility(777) + "%");
	}
}
