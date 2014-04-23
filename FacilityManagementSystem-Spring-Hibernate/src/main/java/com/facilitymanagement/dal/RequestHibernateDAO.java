package com.facilitymanagement.dal;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import com.facilitymanagement.model.facility.ComplexFacility;
import com.facilitymanagement.model.facility.IComplexFacility;
import com.facilitymanagement.model.facility.IRequest;
import com.facilitymanagement.model.facility.Request;

public class RequestHibernateDAO {

	//CREATE
	public void insertRequest(IRequest request){
		System.out.println("********************* Adding Request info in DB with ID ... " + request.getRequestId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(request);
		session.getTransaction().commit();
	}
	
	//RETRIEVE
	public IRequest getRequestById(int rId){
		try{
			System.out.println("*********************** Searching for Request info with ID ... " + rId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			IRequest retReq = (IRequest) session.get(Request.class, rId);
			retReq.getFacility();
			retReq.getUnit();
			retReq.getRequestStatus();
			retReq.getCategory();
			
			session.getTransaction().commit();
			return retReq;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//UPDATE
	public void updateRequest(IRequest request){
		try{
			System.out.println("********************* Updating Request info with ID ... " + request.getRequestId());
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(request);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}	

	public List<IRequest> getAllRequestForFacility(int fId){
		try{
			System.out.println("************************** Searching for All Requests for facility with ID ... " + fId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			Query getAllReqQuery = session.createQuery("FROM Request WHERE facilityId =:fId");
			getAllReqQuery.setInteger("fId", fId);
			
			System.out.println("********************** Retrieve Query is ....>>\n" + getAllReqQuery.toString());
			
			List requests = getAllReqQuery.list();
			System.out.println("Getting All Requests using HQL.   \n");
			
			session.getTransaction().commit();
			
			return requests;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	//DELETE ALL REQUESTS
	public void deleteAllRequests(){
		
		System.out.println("************* Deleting ALL Requests from DB ");
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String deleteAllJobs = "DELETE FROM Request";
		Query deleteQuery = session.createQuery(deleteAllJobs);
		
		System.out.println("************* Delete Query is ....>>\n" + deleteQuery.toString());
		int result = deleteQuery.executeUpdate();
		
		System.out.println("\nRows affected: " + result);
		
		session.getTransaction().commit();
	}
}