package com.facilitymanagement.dal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import com.facilitymanagement.model.maintenance.IMaintenanceJob;
import com.facilitymanagement.model.maintenance.MaintenanceJob;

public class MaintenanceJobHibernateDAO {
	
	//CREATE
	public void insertMaintenancetJob(IMaintenanceJob job){
		System.out.println("*************** Adding MaintenanceJob info in DB with ID ...  " + job.getJobId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(job);
		session.getTransaction().commit();
	}
	
	//DELETE
	public void deleteMaintenanceJob(IMaintenanceJob job) {
		System.out.println("*************** Deleteing MaintenanceJob info in DB with ID ...  " + job.getJobId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(job);
		session.getTransaction().commit();
	}
	
	//RETRIEVE
	public IMaintenanceJob getMaintenanceJobById(String jobId) {
		try {
		System.out.println("*************** Searcing for MaintenanceJob info with ID ...  " + jobId);
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		//System.out.println("*************** Hibernate session is created ..................\n" + session.toString());
		
		//Query getjobQuery = session.createQuery("From jobomerImpl ");
		Query getJobQuery = session.createQuery("FROM MaintenanceJob WHERE jobId=:jobId");		
		getJobQuery.setString("jobId", jobId);
		
		System.out.println("*************** Retrieve Query is ....>>\n" + getJobQuery.toString()); 
		
		List job = getJobQuery.list();
		System.out.println("Getting Book Details using HQL. \n" + job);

		session.getTransaction().commit();
		return (IMaintenanceJob)job.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//UPDATE
	public void updateMaintenanceJob(IMaintenanceJob job){
		try{
			System.out.println("************ Updating MaintenanceJob info with ID ... " + job.getJobId());
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(job);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 *  Utility methods
	 **/
	public List<IMaintenanceJob> getAllRoutineMaintenanceJobs(int fId){
		
		try{
			System.out.println("********************* Searching for ALL jobs for facility with ID ... " + fId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			String sql = "SELECT j.jobid, j.jobdescription, j.jobperformer, j.cost, d.orderdetailid FROM maintenancejob j, orderdetail d, maintenanceorder o, request r" +
				" WHERE j.detailId = d.orderDetailId AND d.orderid = o.orderid AND d.term <> 'SINGLE' AND o.requestId = r.requestId" +
						" AND r.facilityId = " + fId;
	         SQLQuery query = session.createSQLQuery(sql);
	         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
	         
	         List data = query.list();
	         List<IMaintenanceJob> jobs = new ArrayList<IMaintenanceJob>();
	         
	         for(Object object : data)
	         {
	        	IMaintenanceJob job = new MaintenanceJob();
	            Map row = (Map)object;
	            job.setDetailId((Integer) row.get("orderdetailid"));
	            job.setJobDescription((String) row.get("jobdescription"));
	            job.setJobId((Integer) row.get("jobid"));
	            job.setJobPerformer((String)row.get("jobperformer"));
	            job.setPrice((Double) row.get("cost"));
	            jobs.add(job);
	         }
			session.getTransaction().commit();
			return jobs;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public int getTotalNumberJobsForFacility(int fId){
		try{
			
			System.out.println("******************* Get Total Number of Jobs for facility with ID ... " + fId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query getTotalNumJobsQuery = session.createQuery("SELECT COUNT(*) FROM maintenanceorder m, request r"+
						" WHERE m.requestId = r.requestId AND r.facilityId =: fid");
			getTotalNumJobsQuery.setInteger("fid", fId);
			
			System.out.println("******************* Retrieve Query is ....>>\n" + getTotalNumJobsQuery.toString());

			System.out.println("Getting TotNumber of Jobs for Facility using HQL.  \n");
		
			session.getTransaction().commit();
			
			return ((Number) getTotalNumJobsQuery.uniqueResult()).intValue();
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	public int getTotalNumberSevereJobsForFacility(int fId){
		
		try{
			System.out.println("******************* Get Total Number of Jobs for facility with ID ... " + fId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query getSevereJobsQuery = session.createQuery("SELECT COUNT(*) FROM maintenancelog l, orderdetail d, maintenanceorder m, request r" +
					" WHERE l.severityLevel = 'MAJOR' " +
					" AND l.jobId = d.jobId AND d.orderId = m.orderId" +
					" AND m.requestId = r.requestId" +
					" AND r.facilityId =:fid");
			getSevereJobsQuery.setInteger("fid", fId);
			
			System.out.println("******************* Retrieve Query is ....>>\n" + getSevereJobsQuery.toString());

			System.out.println("Getting Number of SevereJobs for Facility using HQL.  \n");
		
			session.getTransaction().commit();
			
			return ((Number) getSevereJobsQuery.uniqueResult()).intValue();
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	//Delete All jobs
	public void deleteAllJobs(){
			
		System.out.println("************* Deleting ALL jobs from DB ");
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String deleteAllJobs = "DELETE FROM MaintenanceJob";
		Query deleteQuery = session.createQuery(deleteAllJobs);
		
		System.out.println("************* Delete Query is ....>>\n" + deleteQuery.toString());
		int result = deleteQuery.executeUpdate();
		
		System.out.println("\nRows affected: " + result);
		
		session.getTransaction().commit();
	}
}