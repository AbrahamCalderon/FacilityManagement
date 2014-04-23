package com.facilitymanagement.dal;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.facilitymanagement.model.user.ITenant;
import com.facilitymanagement.model.user.Tenant;

public class TenantHibernateDAO {
	
	//Create Tenant
	public void insertTenant(ITenant tenant){
		System.out.println("*************** Adding Tenant to DB with ID ...  " + tenant.getTenantId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(tenant);
		session.getTransaction().commit();
	}
	
	//Delete Tenant
	public void deleteTenant(ITenant tenant){
		System.out.println("************** DELETING Tenant FROM DB WITH Ssn#..." + tenant.getTenantId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(tenant);
		session.getTransaction().commit();
	}
	
	//Retrieve Tenant
	public ITenant getTenantById(int tenantId){
		try{
			System.out.println("*************** Searching for Tenant with ID ...  " + tenantId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			Query getTenantQuery = session.createQuery("From Tenant where tenantId=:tenantId");
			getTenantQuery.setInteger("tenantId", tenantId);
			
			System.out.println("Retrieve Query: " + getTenantQuery.toString());
			
			List tenants = getTenantQuery.list();
			System.out.println("Getting tenant info using HQL. \n");
			
			session.getTransaction().commit();
			return (Tenant)tenants.get(0);
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
		
	//Update Tenant
	public void updateTenant(ITenant tenant){
		Session session = null;
		try{
			session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(tenant);
			session.getTransaction().commit();

		}catch (HibernateException e) {
			if(session != null)
				e.printStackTrace();
		}finally{
			session.close();
		}
	}	

	//DELETE ALL TENANTS
	public void deleteAllTenants(){
			
		System.out.println("************* Deleting ALL Tenants from DB ");
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String deleteAllTenants = "DELETE FROM Tenant";
		Query deleteQuery = session.createQuery(deleteAllTenants);
		
		System.out.println("************* Delete Query is ....>>\n" + deleteQuery.toString());
		int result = deleteQuery.executeUpdate();
		
		System.out.println("\nRows affected: " + result);
		
		session.getTransaction().commit();
	}
}
