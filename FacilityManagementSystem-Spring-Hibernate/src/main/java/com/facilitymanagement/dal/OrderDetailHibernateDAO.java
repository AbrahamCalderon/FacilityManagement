package com.facilitymanagement.dal;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import com.facilitymanagement.model.maintenance.IOrderDetail;
import com.facilitymanagement.model.maintenance.SeverityLevel;

public class OrderDetailHibernateDAO {
	
	//CREATE
	public void insertOrderDetail(IOrderDetail detail){
		System.out.println("********************** Adding OrderDetail");
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(detail);
		session.getTransaction().commit();
	}
	
	//RETRIEVE
	public List<IOrderDetail> getOrderDetailByOrderId(int orderId){
		try{
			System.out.println("******************** Searching for OrderDetail info with ID ... " + orderId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			Query getDetailsQuery = session.createQuery("FROM OrderDetail WHERE orderId =:orderId");
			getDetailsQuery.setInteger("orderId", orderId);
			
			System.out.println("*************** Retrieve Query is ....>>\n" + getDetailsQuery.toString()); 
			
			List details = getDetailsQuery.list();
			System.out.println("Getting OrderDetails using HQL. \n" + details);

			session.getTransaction().commit();
			return details;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	
	//UPDATE
	public void updateOrderDetail(IOrderDetail detail){
		try{
			System.out.println("********************** Updating OrderDetail info with ID ... " + detail.getOrderDetailId());
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(detail);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Delete All jobs
	public void deleteAllOrderDetails(){
				
		System.out.println("************* Deleting ALL jobs from DB ");
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String deleteAllOrderDetails = "DELETE FROM MaintOrderDetail";
		Query deleteQuery = session.createQuery(deleteAllOrderDetails);
		
		System.out.println("************* Delete Query is ....>>\n" + deleteQuery.toString());
		int result = deleteQuery.executeUpdate();
		
		System.out.println("\nRows affected: " + result);
		
		session.getTransaction().commit();
	}
	
}
