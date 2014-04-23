package com.facilitymanagement.model.service;

import com.facilitymanagement.dal.AptUnitHibernateDAO;
import com.facilitymanagement.dal.ComplexFacilityHibernateDAO;
import com.facilitymanagement.dal.DetailHibernateDAO;
import com.facilitymanagement.dal.FeatureHibernateDAO;
import com.facilitymanagement.dal.InspectionHibernateDAO;
import com.facilitymanagement.dal.LeaseHibernateDAO;
import com.facilitymanagement.dal.MaintenanceJobHibernateDAO;
import com.facilitymanagement.dal.MaintenanceLogHibernateDAO;
import com.facilitymanagement.dal.MaintenanceOrderHibernateDAO;
import com.facilitymanagement.dal.OrderDetailHibernateDAO;
import com.facilitymanagement.dal.RequestHibernateDAO;
import com.facilitymanagement.dal.ReservationHibernateDAO;
import com.facilitymanagement.dal.TenantHibernateDAO;
import com.facilitymanagement.dal.UserHibernateDAO;

public class UtilityService {
	
	ComplexFacilityHibernateDAO cfDAO = new ComplexFacilityHibernateDAO();
	AptUnitHibernateDAO aptDAO = new AptUnitHibernateDAO();
	RequestHibernateDAO reqDAO = new RequestHibernateDAO();
	MaintenanceOrderHibernateDAO mOrderDAO = new MaintenanceOrderHibernateDAO();
	OrderDetailHibernateDAO orderDetailDAO = new OrderDetailHibernateDAO(); 
	MaintenanceLogHibernateDAO mLogDAO = new MaintenanceLogHibernateDAO();
	MaintenanceJobHibernateDAO mJobDAO = new MaintenanceJobHibernateDAO();
	TenantHibernateDAO tenDAO = new TenantHibernateDAO();
	DetailHibernateDAO detDAO = new DetailHibernateDAO();
	FeatureHibernateDAO fetDAO = new FeatureHibernateDAO();
	InspectionHibernateDAO insDAO = new InspectionHibernateDAO();
	UserHibernateDAO userDAO = new UserHibernateDAO();
	ReservationHibernateDAO resDAO = new ReservationHibernateDAO();
	LeaseHibernateDAO leaseDAO = new LeaseHibernateDAO();
	
	public void removeAllRecords(){

		mJobDAO.deleteAllJobs();
		mLogDAO.deleteAllMaintenanceLogs();
		orderDetailDAO.deleteAllOrderDetails();
		mOrderDAO.deleteAllOrders();
		detDAO.deleteAllUnitDetails();
		detDAO.deleteAllFacilityDetails();
		fetDAO.deleteAllUnitFeatures();
		fetDAO.deleteAllFacilityFeatures();
		insDAO.deleteAllInspections();
		reqDAO.deleteAllRequests();
		userDAO.deleteAllOwners();
		resDAO.DeleteAllReservations();
		tenDAO.deleteAllTenants();
		leaseDAO.deleteAllLeases();
		aptDAO.deleteAllUnits();
		cfDAO.deleteAllComplexFacilities();	
	}
}
