package com.facilitymanagement.dal;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.facilitymanagement.model.user.IOwner;
import com.facilitymanagement.model.user.Owner;

public class UserHibernateDAO {

	//CREATE new owner
	public void insertNewOwner(IOwner owner){
		System.out.println("*************** Adding owner to DB with ID ...  " + owner.getOwnerId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(owner);
		session.getTransaction().commit();
	}
	
	public void deleteOwner(IOwner owner){
		System.out.println("*************** Deleteing owner from DB with ID ...  " + owner.getOwnerId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(owner);
		session.getTransaction().commit();
	}

	public IOwner getOwnerById(int ownerId){
		try{
			System.out.println("*************** Searcing for owner information with ID ...  " + ownerId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			Query getOwnerQuery = session.createQuery("From Owner where ownerId=:ownerId");
			getOwnerQuery.setInteger("ownerId", ownerId);
			
			System.out.println("************* Retrieve Query is .......>>\n" + getOwnerQuery.toString());
			List<IOwner> owners = getOwnerQuery.list();
			System.out.println("Getting owner details using HQL. \n" + owners);
			
			session.getTransaction().commit();
			return (IOwner)owners.get(0);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	public Owner getOwnerByDetId(int detId){
		
		try{
			System.out.println("*************** Searcing for owner info binded to detail object with ID ...  " + detId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			Query getOwnerQuery = session.createQuery("From Owner where detailId=:detId");
			getOwnerQuery.setInteger("detailId", detId);
			
			System.out.println("************* Retrieve Query is .......>>\n" + getOwnerQuery.toString());
			List owners = getOwnerQuery.list();
			System.out.println("Getting owner details using HQL. \n" + owners);
			
			session.getTransaction().commit();
			return (Owner)owners.get(0);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateOwner(IOwner owner){
		Session session = null;
		try{
			session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(owner);
			session.getTransaction().commit();

		}catch (HibernateException e) {
			if(session != null)
				e.printStackTrace();
		}finally{
			session.close();
		}
	}

	//DELETE ALL OWNERS
	public void deleteAllOwners(){
		
		System.out.println("************* Deleting ALL Owners from DB ");
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String deleteAllJobs = "DELETE FROM Owner";
		Query deleteQuery = session.createQuery(deleteAllJobs);
		
		System.out.println("************* Delete Query is ....>>\n" + deleteQuery.toString());
		int result = deleteQuery.executeUpdate();
		
		System.out.println("\nRows affected: " + result);
		
		session.getTransaction().commit();
	}
}
