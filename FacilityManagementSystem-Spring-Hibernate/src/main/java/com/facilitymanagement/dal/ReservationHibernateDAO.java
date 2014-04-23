package com.facilitymanagement.dal;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import com.facilitymanagement.model.facility.IReservation;

public class ReservationHibernateDAO {
	
	//create
	public void insertReservation(IReservation res){
		System.out.println("*************** Adding Reservation to DB with ID ...  " + res.getReservationId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(res);
		session.getTransaction().commit();
	}
	
	//delete
	public void deleteReservation(IReservation res){
		System.out.println("*************** Deleteing Reservation from DB with ID ...  " + res.getReservationId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(res);
		session.getTransaction().commit();		
	}
	
	//retrieve
	public IReservation getReservationById(int reservationId){
		try {
			System.out.println("*************** Searching for Reservation with ID ...  " + reservationId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			//System.out.println("*************** Hibernate session is created ..................\n" + session.toString());
			
			//Query getCustQuery = session.createQuery("From CustomerImpl ");
			Query getCustQuery = session.createQuery("From Reservation where reservationId=:reservationId");		
			getCustQuery.setInteger("reservationId", reservationId);
			
			System.out.println("*************** Retrieve Query is ....>>\n" + getCustQuery.toString()); 
			
			List<IReservation> reservations = getCustQuery.list();
			System.out.println("Getting reservation details using HQL. \n" + reservations);

			session.getTransaction().commit();
			return (IReservation)reservations.get(0);
			} catch (HibernateException e) {
				e.printStackTrace();
			}
			return null;
	}
		//update
		public void updateReservation(IReservation res){
			Session session = null;
			try{
				session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				session.update(res);
				session.getTransaction().commit();
			}catch (HibernateException e) {
				if(session != null)
					e.printStackTrace();
			}finally{
				session.close();
			}	
		}

		//GET reservations for aptUnit
		public List<IReservation> getReservationForUnitById(int aptId){
			try{
				System.out.println("*************** Searcing for Reservations that belong to Unit ID...  " + aptId);
				Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				
				Query getReservationQuery = session.createQuery("From Reservation where aptId=:aptId");
				getReservationQuery.setInteger("aptId", aptId);
				
				System.out.println("********************** RETRIEVE QUERY: " + getReservationQuery.toString());
				
				List<IReservation> reservations = getReservationQuery.list();
				System.out.println("Getting inspection details using HQL. \n" + reservations);
				
				session.getTransaction().commit();
				return reservations;
				
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			
			return null;
		}

		//DELETE ALL RESERVATIONS
		public void DeleteAllReservations(){
					
				System.out.println("************* Deleting ALL Reservations from DB ");
				Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				
				String deleteAllRes = "DELETE FROM Reservation";
				Query deleteQuery = session.createQuery(deleteAllRes);
				
				System.out.println("************* Delete Query is ....>>\n" + deleteQuery.toString());
				int result = deleteQuery.executeUpdate();
				
				System.out.println("\nRows affected: " + result);
				
				session.getTransaction().commit();
		}
}
