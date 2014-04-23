package com.facilitymanagement.dal;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.facilitymanagement.model.facility.IFacilityDetail;
import com.facilitymanagement.model.facility.IUnitDetail;

public class DetailHibernateDAO {
	
	/********************************************************************************************/
	/******************************** UNIT DETAIL ***********************************************/
	
	//Create Unit Detail
	public void insertAptUnitDetail(IUnitDetail aptDetail){
		System.out.println("*************** Adding unit detail to DB with ID ...  " + aptDetail.getAptId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(aptDetail);
		session.getTransaction().commit();
	}
		
	//Delete Unit Detail
	public void deleteAptDetail(IUnitDetail aptDetail){
		System.out.println("*************** Deleting inspection from DB with ID ...  " + aptDetail.getAptId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(aptDetail);
		session.getTransaction().commit();
	}
		
	//Get Unit Detail by ID
	public IUnitDetail getAptDetailById(int aptDetId){
		try{
			System.out.println("*************** Searching for unit detail with detail ID ...  " + aptDetId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			Query getAptUnitDetailQuery = session.createQuery("From AptUnitDetail where aptDetId=:aptDetId");
			getAptUnitDetailQuery.setInteger("aptDetId", aptDetId);
			
			System.out.println("********************** RETRIEVE QUERY: " + getAptUnitDetailQuery.toString());
			
			List<IUnitDetail> details = getAptUnitDetailQuery.list();
			System.out.println("Getting apt detail using HQL. \n" + details);
			
			session.getTransaction().commit();
			return (IUnitDetail)details.get(0);
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Update Unit Detail
	public void updateAptDetail(IUnitDetail aptDetail){
		Session session = null;
		try{
			session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(aptDetail);
			session.getTransaction().commit();
			}catch (HibernateException e) {
			if(session != null)
				e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	
	/********************************************************************************************/
	/******************************** FACILITY DETAIL ********************************************/
	
	//Create inspection
	public void insertFacilityDetail(IFacilityDetail facDetail){
		System.out.println("*************** Adding unit detail to DB with ID ...  " + facDetail.getFacDetId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(facDetail);
		//session.save(facDetail.getUsages());
		session.getTransaction().commit();
	}
			
	//Delete inspection
	public void deleteFacilityDetail(IFacilityDetail facDetail){
		System.out.println("*************** Deleting inspection from DB with ID ...  " + facDetail.getFacDetId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(facDetail);
		session.getTransaction().commit();
	}
		
	//Get AptDetail by id
	public IFacilityDetail getFacilityDetailById(int facDetId){
		try{
			System.out.println("*************** Searching for unit detail with detail ID ...  " + facDetId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			Query getFacilityDetailQuery = session.createQuery("From FacilityDetail where facDetId=:facDetId");
			getFacilityDetailQuery.setInteger("facDetId", facDetId);
			
			System.out.println("********************** RETRIEVE QUERY: " + getFacilityDetailQuery.toString());
			
			List<IFacilityDetail> details = getFacilityDetailQuery.list();
			System.out.println("Getting apt detail using HQL. \n" + details);
			
			session.getTransaction().commit();
			return (IFacilityDetail)details.get(0);
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Update
	public void updateFacilityDetail(IFacilityDetail facDetail){
		Session session = null;
		try{
			session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(facDetail);
			session.getTransaction().commit();
		}catch (HibernateException e) {
			if(session != null)
				e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	//Delete All unit details
	public void deleteAllUnitDetails(){
				
		System.out.println("************* Deleting ALL UnitDetails from DB ");
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String deleteAllUnitDetails = "DELETE FROM AptUnitDetail";
		Query deleteQuery = session.createQuery(deleteAllUnitDetails);
		
		System.out.println("************* Delete Query is ....>>\n" + deleteQuery.toString());
		int result = deleteQuery.executeUpdate();
		
		System.out.println("\nRows affected: " + result);
		
		session.getTransaction().commit();
	}
	
	//Delete All unit details
	public void deleteAllFacilityDetails(){
				
		System.out.println("************* Deleting ALL FacDetails from DB ");
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String deleteAllUnitDetails = "DELETE FROM FacilityDetail";
		Query deleteQuery = session.createQuery(deleteAllUnitDetails);
		
		System.out.println("************* Delete Query is ....>>\n" + deleteQuery.toString());
		int result = deleteQuery.executeUpdate();
		
		System.out.println("\nRows affected: " + result);
		
		session.getTransaction().commit();
	}
}

