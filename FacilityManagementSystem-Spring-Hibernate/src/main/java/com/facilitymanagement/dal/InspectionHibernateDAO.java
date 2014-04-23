package com.facilitymanagement.dal;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.facilitymanagement.model.facility.IInspection;
import com.facilitymanagement.model.facility.Inspection;

public class InspectionHibernateDAO {
	
	//Create inspection
	public void insertInspection(IInspection inspection){
		System.out.println("*************** Adding Inspection to DB with ID ...  " + inspection.getInspectionId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(inspection);
		session.getTransaction().commit();
	}
	
	//Delete inspection
	public void deleteInspection(IInspection inspection){
		System.out.println("*************** Deleting inspection from DB with ID ...  " + inspection.getInspectionId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(inspection);
		session.getTransaction().commit();
	}
		
	//Retrieve inspection
	public IInspection getInspectionById(int inspectionId){
		try{
			System.out.println("*************** Searcing for Inspection with ID ...  " + inspectionId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			Query getInspectionQuery = session.createQuery("From Inspection where inspectionId=:inspectionId");
			getInspectionQuery.setInteger("inspectionId", inspectionId);
			
			System.out.println("********************** RETRIEVE QUERY: " + getInspectionQuery.toString());
			
			List inspections = getInspectionQuery.list();
			System.out.println("Getting inspection details using HQL. \n" + inspections);
			
			session.getTransaction().commit();
			return (Inspection)inspections.get(0);
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
		
	//Retrieve all inspections for a given unit
	public List<IInspection> getInspectionForComplex(int fId){
		try{
			System.out.println("*************** Searcing for Inspection that is binded complex ID ...  " + fId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			Query getInspectionQuery = session.createQuery("From Inspection where facilityId=:facilityId");
			getInspectionQuery.setInteger("facilityId", fId);
			
			System.out.println("********************** RETRIEVE QUERY: " + getInspectionQuery.toString());
			
			List<IInspection> inspections = getInspectionQuery.list();
			System.out.println("Getting inspection details using HQL. \n" + inspections);
			
			session.getTransaction().commit();
			return inspections;
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return null;
	}
		
	//Update
	public void updateInspection(IInspection inspection){
		Session session = null;
		try{
			session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(inspection);
			session.getTransaction().commit();

		}catch (HibernateException e) {
			if(session != null)
				e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	//DELETE ALL INSPECTIONS
	public void deleteAllInspections(){
		
		System.out.println("************* Deleting ALL Inspections from DB ");
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String deleteAllJobs = "DELETE FROM Inspection";
		Query deleteQuery = session.createQuery(deleteAllJobs);
		
		System.out.println("************* Delete Query is ....>>\n" + deleteQuery.toString());
		int result = deleteQuery.executeUpdate();
		
		System.out.println("\nRows affected: " + result);
		
		session.getTransaction().commit();
	}
}
