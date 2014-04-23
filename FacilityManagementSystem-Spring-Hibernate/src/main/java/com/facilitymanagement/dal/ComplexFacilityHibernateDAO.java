package com.facilitymanagement.dal;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.facilitymanagement.model.facility.ComplexFacility;
import com.facilitymanagement.model.facility.IComplexFacility;
import com.facilitymanagement.model.user.IOwner;

public class ComplexFacilityHibernateDAO {

	//Create
	public void insertNewComplexFacility(IComplexFacility fac){
		System.out.println("************* Adding new facility to DB with an ID..." + fac.getFacilityId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(fac);
		session.getTransaction().commit();
	}
	
	//Delete
	public void deleteComplexFacility(IComplexFacility fac){
		System.out.println("************* Deleting facility from DB with ID..." + fac.getFacilityId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(fac);
		session.getTransaction().commit();
	}
	
	//Retreive
	public IComplexFacility getComplexFacilityById(int facilityId){
		try{
			System.out.println("**************** Searching for facility with ID..." + facilityId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			IComplexFacility retFac = (IComplexFacility) session.get(ComplexFacility.class, facilityId);
			retFac.getUnits().size();
			retFac.getInspections().size();
			retFac.getFeatures().size();
			retFac.getOwner();
			retFac.getDetail();
			
			session.getTransaction().commit();
			
			//System.out.println("Unit Size::: " + retFac.getUnits().size());
			
			return retFac;
		}catch(HibernateException e){
			System.out.println("Failed retreiving complex.");
			e.printStackTrace();
		}
		return null;
	}
	
	//update
	public void updateComplexFacility(IComplexFacility facility){
		System.out.println("********* Updating complex facility with ID..." + facility.getFacilityId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(facility);
		session.getTransaction().commit();
	}
	
	//Get all facilities
	public List<IComplexFacility> getAllComplexFacilities(){
		try{
			System.out.println("**************** Searching for all facility...");
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			Query getFacilityQuery = session.createQuery("From ComplexFacility");
			
			System.out.println("************* Retrieve Query is ....>>\n" + getFacilityQuery.toString());
			
			List<IComplexFacility> facilities = getFacilityQuery.list();
			System.out.println("Getting facility using HQL. \n" + facilities);
			
			session.getTransaction().commit();
			return facilities;
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return null;
	}
	
	//DELETE ALL COMPLEX FACILITIES
	public void deleteAllComplexFacilities(){
		
		System.out.println("************* Deleting ALL Facilities from DB ");
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String deleteAllFacs = "DELETE FROM ComplexFacility";
		Query deleteQuery = session.createQuery(deleteAllFacs);
		
		System.out.println("************* Delete Query is ....>>\n" + deleteQuery.toString());
		int result = deleteQuery.executeUpdate();
		
		System.out.println("\nRows affected: " + result);
		
		session.getTransaction().commit();
	}
}
