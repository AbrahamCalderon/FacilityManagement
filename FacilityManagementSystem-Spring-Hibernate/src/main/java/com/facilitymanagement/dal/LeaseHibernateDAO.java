package com.facilitymanagement.dal;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.facilitymanagement.model.facility.ILease;

public class LeaseHibernateDAO {

	//CREATE lease
	public void insertLease(ILease lease){
		System.out.println("*************** Adding lease to DB with ID ...  " + lease.getLeaseId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(lease);
		session.getTransaction().commit();		
	}
	
	//Delete
	public void deleteLease(ILease lease){
		System.out.println(">>>>>lease date moveInDate: " + lease.getMoveInDate());
		System.out.println("*************** Deleteing lease information in DB with ID ...  " + lease.getLeaseId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(lease);
		session.getTransaction().commit();
	}

	//RETRIEVE lease 
	public ILease getLivingAgreementById(int leaseId){
		try {
			System.out.println("*************** Searching for lease information with ID ...  " + leaseId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			//System.out.println("*************** Hibernate session is created ..................\n" + session.toString());
			
			//Query getCustQuery = session.createQuery("From CustomerImpl ");
			Query getLeaseQuery = session.createQuery("From Lease where leaseId=:leaseId");		
			getLeaseQuery.setInteger("leaseId", leaseId);
			
			System.out.println("*************** Retrieve Query is ....>>\n" + getLeaseQuery.toString()); 
			
			List<ILease> leases = getLeaseQuery.list();
			System.out.println("Getting Book Details using HQL. \n" + leases);

			session.getTransaction().commit();
			return (ILease)leases.get(0);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
		
	//UPDATE lease
	public void updateLivingAgreement(ILease lease){
		Session session = null;
		try{
			session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(lease);
			session.getTransaction().commit();

		}catch (HibernateException e) {
			if(session != null)
				e.printStackTrace();
		}finally{
			session.close();
		}
	}
		
	//GET ALL LEASES ASSOCIATED FOR A GIVEN UNIT
	public List<ILease> getLivingAgreementByUnitId(int aptId) {
		try{
			System.out.println("*************** Searcing for leases that are binded to Unit ID ...  " + aptId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			Query getLeasesQuery = session.createQuery("From Lease where aptId=:aptId");
			getLeasesQuery.setInteger("aptId", aptId);
			
			System.out.println("********************** RETRIEVE QUERY: " + getLeasesQuery.toString());
			
			List<ILease> leases = getLeasesQuery.list();
			System.out.println("Getting inspection details using HQL. \n" + leases);
			
			session.getTransaction().commit();
			return leases;
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	//Delete ALL LEASES
	public void deleteAllLeases(){
			
		System.out.println("************* Deleting ALL Leases from DB ");
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String deleteAllLeases = "DELETE FROM Lease";
		Query deleteQuery = session.createQuery(deleteAllLeases);
		
		System.out.println("************* Delete Query is ....>>\n" + deleteQuery.toString());
		int result = deleteQuery.executeUpdate();
		
		System.out.println("\nRows affected: " + result);
		
		session.getTransaction().commit();
	}	
}
