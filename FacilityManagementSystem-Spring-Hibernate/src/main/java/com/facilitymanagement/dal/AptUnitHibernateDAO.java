package com.facilitymanagement.dal;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.facilitymanagement.model.facility.AptUnit;
import com.facilitymanagement.model.facility.ComplexFacility;
import com.facilitymanagement.model.facility.IAptUnit;
import com.facilitymanagement.model.facility.IComplexFacility;
import com.facilitymanagement.model.facility.Inspection;

public class AptUnitHibernateDAO {
	
	//Create
	public void insertAptUnit(IAptUnit aptUnit){
		System.out.println("*************** Adding apartment unit to DB with ID ...  " + aptUnit.getUnitId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(aptUnit);
		session.getTransaction().commit();
	}
	
	//Delete
	public void deleteAptUnit(IAptUnit aptUnit){
		System.out.println("*************** Deleteing apartment unit from DB with ID..." + aptUnit.getUnitId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(aptUnit);
		session.getTransaction().commit();
	}
	
	//Retrieve
	public IAptUnit getAptUnitById(int aptId){
		System.out.println("*************** Retrieving apartment unit with ID..." + aptId);
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		IAptUnit retApt = (IAptUnit) session.get(AptUnit.class, aptId);
		retApt.getDetail();
		retApt.getFeatures().size();
		retApt.getFutureReservations().size();
		retApt.getLeases().size();
		
		session.getTransaction().commit();
		return retApt;
	}
	
	//update
	public void updateAptUnit(IAptUnit aptUnit){
		System.out.println("********** Updating apartment unit with ID..." + aptUnit.getUnitId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(aptUnit);
		session.getTransaction().commit();
	}
	
	//Delete All jobs
	public void deleteAllUnits(){
				
		System.out.println("************* Deleting ALL units from DB ");
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String deleteAllUnits = "DELETE FROM AptUnit";
		Query deleteQuery = session.createQuery(deleteAllUnits);
		
		System.out.println("************* Delete Query is ....>>\n" + deleteQuery.toString());
		int result = deleteQuery.executeUpdate();
	
		System.out.println("\nRows affected: " + result);
		
		session.getTransaction().commit();
	}
}
