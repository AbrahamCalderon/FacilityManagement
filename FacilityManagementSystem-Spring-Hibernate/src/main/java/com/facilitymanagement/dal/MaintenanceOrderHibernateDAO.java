package com.facilitymanagement.dal;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.facilitymanagement.model.facility.ComplexFacility;
import com.facilitymanagement.model.facility.IComplexFacility;
import com.facilitymanagement.model.maintenance.IMaintenanceJob;
import com.facilitymanagement.model.maintenance.IOrder;
import com.facilitymanagement.model.maintenance.MaintenanceJob;
import com.facilitymanagement.model.maintenance.MaintenanceOrder;
import com.facilitymanagement.model.maintenance.SeverityLevel;

public class MaintenanceOrderHibernateDAO {

	//CREATE
	public void insertMaintenanceOrder(IOrder mOrder){
		System.out.println("*************** Adding MaintenanceOrder info in DB with ID ... " + mOrder.getOrderId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		session.save(mOrder);
		session.getTransaction().commit();
	}
	
	//RETRIEVE
	public IOrder getOrderById(int oId){
		try{
			System.out.println("*************** Searching for Order info with ID ... " + oId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			IOrder retOrder = (IOrder) session.get(MaintenanceOrder.class, oId);
			retOrder.getOrderDetails().size();
			retOrder.getRequest();
			retOrder.getStatus();
			retOrder.getTotalCost();
			
			session.getTransaction().commit();
			return retOrder;
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	//UPDATE
	public void updateMaintenanceOrder(IOrder mOrder){
		try{
			System.out.println("******************* Updating MaintenanceOrder info with ID ... " + mOrder.getOrderId());
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(mOrder);
			session.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	//DELETE ALL ORDERS
	public void deleteAllOrders(){
			
		System.out.println("************* Deleting ALL orders from DB ");
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String deleteAllOrders = "DELETE FROM MaintenanceOrder";
		Query deleteQuery = session.createQuery(deleteAllOrders);
		
		System.out.println("************* Delete Query is ....>>\n" + deleteQuery.toString());
		int result = deleteQuery.executeUpdate();
		
		System.out.println("\nRows affected: " + result);
		
		session.getTransaction().commit();
	}
	

	
	/*****************************************************************************/
	/************************* MANAGEMENT TASKS **********************************/
	
	//QQ - Get total number of jobs (in general) for a given facility
	public double getTotalNumberJobsForFacility(int fid){
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		try{
			//getOrder
			session.beginTransaction();
			String getNoTotalOrders = 
					"SELECT orderId FROM  maintenanceorder m, request r"+
					" WHERE m.requestId = r.requestId" +
							" AND r.facilityId = " + fid;
			SQLQuery query = session.createSQLQuery(getNoTotalOrders);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List orders = query.list();
			
			double totalCount = 0;
			//Get orders				
			for(Object object: orders){
				totalCount++;
			}
			session.getTransaction().commit();
			
			return totalCount;
			
		}catch(HibernateException e){
			System.err.println("OrderDetailDAO: Threw SQLException when retrieving total number job count");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}
	

	//get total number of severe jobs for a given facility
	public double getTotalNumberSevereJobsForFacility(int fid){
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		try{
			//getOrder
			session.beginTransaction();
			String getNoOfAllOrders = 
	"SELECT log_Id FROM maintenancelog l, maintenanceorder m, request r" +
			" WHERE l.severity_Level = '" + SeverityLevel.MAJOR.toString() + "' " +
			" AND l.orderId = m.orderId AND m.requestId = r.requestId" +
			" AND r.facilityId = " + fid;
			
			SQLQuery query = session.createSQLQuery(getNoOfAllOrders);
			List orders = query.list();

			double totalCount = 0;
			
			//Get orders				
			for(Object object: orders){
				totalCount++;
			}
			session.getTransaction().commit();
			return totalCount;
			
		}catch(HibernateException e){
			System.err.println("MaintenanceHibernateDAO: Threw SQLException when retrieving total severe count");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}
	
	//GET ALL MAJOR-level MAINTENANCE ORDERS FOR A GIVEN FACILITY AND RETURN: SUM Duration time
	public int getDownTimeForFacility(int fid){
		
		try{
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			//getOrder
			session.beginTransaction();
			String getNoOfAllOrders = 
	"SELECT l.duration_min FROM maintenancelog l, maintenanceorder m, request r" +
			" WHERE l.severity_Level = '" + SeverityLevel.MAJOR.toString() + "' " +
			" AND l.orderId = m.orderId AND m.requestId = r.requestId" +
			" AND r.facilityId = " + fid;
			
			SQLQuery query = session.createSQLQuery(getNoOfAllOrders);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List orders = query.list();

			int totalDownTime = 0;
			
			//Get orders				
			for(Object duration: orders){
				Map row = (Map)duration;
				Integer time = (Integer) row.get("duration_min");
				totalDownTime+= time.intValue();
			}
			session.getTransaction().commit();
			return totalDownTime;
			
		}catch(HibernateException e){
			System.err.println("MaintenanceHibernateDAO: Threw SQLException when retrieving total severe count");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return 0;
			
}
	
	//Get Facility problems (maintence issues that have severity level of "Major')
	public List<String> getMajorIssuesForFacilityById(int fid){
		
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		
		try{
			//getOrder
			session.beginTransaction();
			String getNoOfAllOrders = 
	"SELECT j.jobDescription FROM maintenancelog l, maintenanceorder m, request r, maintenancejob j, orderDetail od" +
			" WHERE l.severity_Level = '" + SeverityLevel.MAJOR.toString() + "' " +
			"AND j.detailId = od.orderDetailId AND od.orderId = m.orderId" +
			" AND m.orderId = l.orderId AND m.requestId = r.requestId" +
			" AND r.facilityId = " + fid;
			
			SQLQuery query = session.createSQLQuery(getNoOfAllOrders);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			
			List data = query.list();
			List<String> issues = new ArrayList<String>();
			
			//Get orders				
			for(Object i: data){
				Map row = (Map) i;
				issues.add(row.get("jobDescription").toString());
			}
			session.getTransaction().commit();
			return issues;
			
		}catch(HibernateException e){
			System.err.println("MaintenanceHibernateDAO: Threw SQLException when retrieving total severe count");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}	
	
////////////////////////////////////////////////////////////////////////////////	
	
	/**
	 **  Utility Methods for MaintenanceOrder(s)
	 **/
	
	public List<IOrder> getAllOrdersForFacility(int fId){
		
		try{
			System.out.println("*************** Searching for Order info with ID ... " + fId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			String sql = "SELECT m.orderid FROM maintenanceorder m, request r " +
					" WHERE m.requestid = r.requestid AND r.facilityid = " + fId;
		         SQLQuery query = session.createSQLQuery(sql);
		         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		         
		         List data = query.list();
		         List<Integer> orderId = new ArrayList<Integer>();
		         
		         for(Object object : data)
		         {
		        	Map row = (Map)object;
		        	Integer id = (Integer) row.get("orderid");
		        	orderId.add(id);
		         }			
			
			session.getTransaction().commit();
			
			List<IOrder> orders = new ArrayList<IOrder>();
			
			for(int id : orderId){
				IOrder o = getOrderById(id);
				orders.add(o);
			}
						
			return orders;
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;		
	}

	public List<IOrder> getOrdersForFacilityBasedOnTwoDates(int fId, Date begDate, Date endDate){
		try{
			System.out.println("************************* Searching for Order with ID ... " + fId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query getOrderQuery = session.createQuery("SELECT m FROM maintenanceorder m, request r" +
			" WHERE m.requestId = r.requestId AND m.orderDate >= ? AND m.orderDate <= ?" +
			" AND r.facilityId = ?");
			getOrderQuery.setDate(0, begDate);
			getOrderQuery.setDate(1, endDate);
			getOrderQuery.setInteger(2, fId);
			
			List facOrders = getOrderQuery.list();
			System.out.println("Getting Order Details using HQL.  \n" + facOrders);
			
			session.getTransaction().commit();
			
			return facOrders;
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}
