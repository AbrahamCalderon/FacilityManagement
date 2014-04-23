package com.facilitymanagement.dal;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.facilitymanagement.model.facility.IComplexFacility;
import com.facilitymanagement.model.maintenance.IMaintenanceLog;

public class MaintenanceLogHibernateDAO {

	//CREATE
	public void insertMaitenanceLog(IMaintenanceLog log){
		System.out.println("***************************** Adding MaintenanceLog info in DB with ID ... " + log.getLogId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(log);
		session.getTransaction().commit();
	}
	
	//RETRIEVE
	public IMaintenanceLog getMaintenanceLogById(int logId){
		try{
			System.out.println("************************ Searching for MaintenanceLog info with ID ... " + logId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			Query getLogQuery = session.createQuery("FROM MaintenanceLog WHERE log_id=:logId");
			getLogQuery.setInteger("logId", logId);
			System.out.println("*************** Retrieve Query is ....>>\n" + getLogQuery.toString()); 
			List log = getLogQuery.list();
			System.out.println("Getting log details using HQL.  \n" + log);
			
			session.getTransaction().commit();
			return (IMaintenanceLog)log.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//UPDATE
	public void updateMaintenanceLog(IMaintenanceLog log){
		try{
			System.out.println("****************** Updating MaintenanceLog info with ID ... " + log.getLogId());
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(log);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//DELETE ALL MAINTENANCE LOGS
	//DELETE
	public void deleteAllMaintenanceLogs(){
			
		System.out.println("************* Deleting ALL Facilities from DB ");
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String deleteAllLogs = "DELETE FROM MaintenanceLog";
		Query deleteQuery = session.createQuery(deleteAllLogs);
			
		System.out.println("************* Delete Query is ....>>\n" + deleteQuery.toString());
		int result = deleteQuery.executeUpdate();
		
		System.out.println("\nRows affected: " + result);
		
		session.getTransaction().commit();
	}
}