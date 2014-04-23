package com.facilitymanagement.dal;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.facilitymanagement.model.facility.FacilityFeature;
import com.facilitymanagement.model.facility.UnitFeature;

public class FeatureHibernateDAO {

	/********************************************************************************************/
	/***************************** Persisting Unit Feature **************************************/
	//Create
	public void insertNewUnitFeature(UnitFeature feature){
		System.out.println("************ Adding apartment unit to DB with ID..." + feature.getFeatureId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(feature);
		session.getTransaction().commit();
	}
	
	//Delete
	public void deleteUnitFeature(UnitFeature feature){
		System.out.println("*********** Deleting apartment unit from DB with ID..." + feature.getFeatureId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(feature);
		session.getTransaction().commit();
		
	}
	
	//Retrieve
	public UnitFeature getUnitFeatureById(int featureId){
		try{
		System.out.println("************** Retrieving unit feature from DB with ID..." + featureId);
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query getFeatureQuery = session.createQuery("From UnitFeature where featureId=:featureId");
		getFeatureQuery.setInteger("featureId", featureId);
		
		System.out.println("*************** Retrieve Query is ....>>\n" + getFeatureQuery.toString()); 
		
		List<UnitFeature> features = getFeatureQuery.list();
		System.out.println("Getting Book Details using HQL. \n" + features);

		session.getTransaction().commit();
		return (UnitFeature)features.get(0);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Update
	public void updateUnitFeature(UnitFeature unitFeature){
		System.out.println("Updating unit feature with an ID..." + unitFeature.getFeatureId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(unitFeature);
		session.getTransaction().commit();
	}
	
	/********************************************************************************************/
	/***************************** Persisting Facility Feature **********************************/
	//Create
		public void insertNewFacilityFeature(FacilityFeature feature){
			System.out.println("************ Adding apartment feature to DB with ID..." + feature.getFeatureId());
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(feature);
			session.getTransaction().commit();
		}
		
		//Delete
		public void deleteFacilityFeature(FacilityFeature feature){
			System.out.println("*********** Deleting facility feature from DB with ID..." + feature.getFeatureId());
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.delete(feature);
			session.getTransaction().commit();
			
		}
		
		//Retrieve
		public FacilityFeature getFacilityFeatureById(int featureId){
			try{
			System.out.println("************** Retrieving facility feature from DB with ID..." + featureId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			Query getFeatureQuery = session.createQuery("From FacilityFeature where featureId=:featureId");
			getFeatureQuery.setInteger("featureId", featureId);
			
			System.out.println("*************** Retrieve Query is ....>>\n" + getFeatureQuery.toString()); 
			
			List<FacilityFeature> features = getFeatureQuery.list();
			System.out.println("Getting facility feature using HQL. \n" + features);

			session.getTransaction().commit();
			return (FacilityFeature)features.get(0);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//Update
		public void updateFacilityFeature(FacilityFeature facilityFeature){
			System.out.println("Updating unit feature with an ID..." + facilityFeature.getFeatureId());
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(facilityFeature);
			session.getTransaction().commit();
		}


		/**DELETE ALL METHODS*/
		//DELETE ALL UNIT FEATUES
		public void deleteAllUnitFeatures(){
			
			System.out.println("************* Deleting ALL UnitFeatures from DB ");
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			String deleteAllJobs = "DELETE FROM UnitFeature";
			Query deleteQuery = session.createQuery(deleteAllJobs);
			
			System.out.println("************* Delete Query is ....>>\n" + deleteQuery.toString());
			int result = deleteQuery.executeUpdate();
			
			System.out.println("\nRows affected: " + result);
			
			session.getTransaction().commit();
		}
		
		//DELETE ALL FACILITY FEATURES 
		public void deleteAllFacilityFeatures(){
			
			System.out.println("************* Deleting ALL FacFeatures from DB ");
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			String deleteAllJobs = "DELETE FROM FacilityFeature";
			Query deleteQuery = session.createQuery(deleteAllJobs);
			
			System.out.println("************* Delete Query is ....>>\n" + deleteQuery.toString());
			int result = deleteQuery.executeUpdate();
			
			System.out.println("\nRows affected: " + result);
			
			session.getTransaction().commit();
		}
}
