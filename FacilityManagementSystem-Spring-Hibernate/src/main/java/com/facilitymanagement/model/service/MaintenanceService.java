package com.facilitymanagement.model.service;

import java.sql.Date;
import java.util.List;

import com.facilitymanagement.dal.ComplexFacilityHibernateDAO;
import com.facilitymanagement.dal.MaintenanceJobHibernateDAO;
import com.facilitymanagement.dal.MaintenanceLogHibernateDAO;
import com.facilitymanagement.dal.MaintenanceOrderHibernateDAO;
import com.facilitymanagement.dal.RequestHibernateDAO;
import com.facilitymanagement.model.facility.ComplexFacility;
import com.facilitymanagement.model.facility.IAptUnit;
import com.facilitymanagement.model.facility.IComplexFacility;
import com.facilitymanagement.model.facility.IRequest;
import com.facilitymanagement.model.facility.RequestStatus;
import com.facilitymanagement.model.facility.UnitStatus;
import com.facilitymanagement.model.maintenance.IMaintenanceJob;
import com.facilitymanagement.model.maintenance.IMaintenanceLog;
import com.facilitymanagement.model.maintenance.IOrder;
import com.facilitymanagement.model.maintenance.IOrderDetail;
import com.facilitymanagement.model.maintenance.MOrderStatus;

public class MaintenanceService {
	
	RequestHibernateDAO reqDAO = new RequestHibernateDAO();
	MaintenanceOrderHibernateDAO orderDAO = new MaintenanceOrderHibernateDAO();
	MaintenanceJobHibernateDAO jobDAO = new MaintenanceJobHibernateDAO();
	MaintenanceLogHibernateDAO logDAO = new MaintenanceLogHibernateDAO();
	ComplexFacilityHibernateDAO facDAO = new ComplexFacilityHibernateDAO();
	
	//CREATE --OR-- ADD to facility maintenance table
	public void makeMaintenanceRequest(IRequest request){
		request.setRequestStatus(RequestStatus.PENDING);
		reqDAO.insertRequest(request);
	}
	
	public void scheduleMaintenance(IRequest request){
		request.setRequestStatus(RequestStatus.PROCESSING);
		reqDAO.updateRequest(request);
	}
	
	public void processOrder(IMaintenanceLog log){
		log.getOrder().getRequest().setRequestStatus(RequestStatus.PROCESSING);
		log.getOrder().calculateTotalCost();
		reqDAO.updateRequest(log.getOrder().getRequest());
		logDAO.insertMaitenanceLog(log);
	}
	
	public double calculateMaintenanceCostForFacility(IComplexFacility facility, Date begDate, Date endDate){
	
		//List<MaintenanceOrder> order = DAo.getOrderForIdWithDates()
		//iterate through list and compute costs
		List<IOrder> specificOrders = 
				orderDAO.getOrdersForFacilityBasedOnTwoDates(facility.getFacilityId(), begDate, endDate);
		double retCost = 0;
		for (IOrder o : specificOrders){
			retCost += o.getTotalCost();
		}		
		return retCost;		
	}
	
	public double calculateMaintenanceCostForFacility(IComplexFacility facility){
		
		List<IOrder> allOrders = 
				orderDAO.getAllOrdersForFacility(facility.getFacilityId());
		double retCost = 0;
		for (IOrder o : allOrders){
			for(IOrderDetail det : o.getOrderDetails()){
				System.out.println(det.getJob().getJobPerformer() + "\t\t@   $" + det.getJob().getPrice() + " / " + det.getTerm());
			}
			retCost += o.getTotalCost();
		}		
		return retCost;	
	}

	public double calcDownTimeForFacility(IComplexFacility facility){
		//return total duration times for all orders in the list
		return orderDAO.getDownTimeForFacility(facility.getFacilityId());		
	}
	
	public List<IRequest> listMaintRequestsForFacility(IComplexFacility facility){
		//DAO: get all requests for the given facility
		return reqDAO.getAllRequestForFacility(facility.getFacilityId());
	}
	
	public List<IMaintenanceJob> listRoutineMaintenanceForFacility(IComplexFacility facility){
		return jobDAO.getAllRoutineMaintenanceJobs(facility.getFacilityId());
		
	}
	
	public List<IOrder> listAllMaintenanceOrdersForFacility(IComplexFacility facility){
		return orderDAO.getAllOrdersForFacility(facility.getFacilityId());
	}
	
	public IRequest getRequestById(int rId){
		return reqDAO.getRequestById(rId);
	}


	public double calcProblemRateForFacility(ComplexFacility facility){
		int total = jobDAO.getTotalNumberJobsForFacility(facility.getFacilityId());
		int totalProb = jobDAO.getTotalNumberSevereJobsForFacility(facility.getFacilityId());
		return totalProb/total;	
	}
	
	public IOrder getOrderById(int orderId){
		return(orderDAO.getOrderById(orderId));
	}
	
	public IMaintenanceLog getMaintenanceLog(int logId){
		return(logDAO.getMaintenanceLogById(logId));
	}
	
	public void completJob(IMaintenanceLog log, java.sql.Date date, int duration){
		log.setDate(date);
		log.setDuration(duration);
		log.getOrder().setStatus(MOrderStatus.COMPLETED);
		logDAO.updateMaintenanceLog(log);
	}
	
	public List<String> retrieveMajorIssuesForFacilityById(int fid){
		return orderDAO.getMajorIssuesForFacilityById(fid);
	}
	
	public double getProblemRateForFacilityById(int facId){
		double totalJobs = orderDAO.getTotalNumberJobsForFacility(facId);
		double totalProblems = orderDAO.getTotalNumberSevereJobsForFacility(facId);
		return (totalProblems/totalJobs)*100;
	}
	
	public double getUsageRateForFacility(int facId){
		IComplexFacility retFac = facDAO.getComplexFacilityById(facId);
		int totalUnits = retFac.getUnits().size();
		int occupiedUnits = 0;
		for(IAptUnit a : retFac.getUnits()){
			if(a.getStatus() != UnitStatus.UNAVAILABLE || a.getStatus() != UnitStatus.VACANT){
				occupiedUnits++;
			}
		}
		
		return (occupiedUnits/totalUnits)*100;
	}
}
