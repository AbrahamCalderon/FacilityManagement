package com.facilitymanagement.model.maintenance;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.facilitymanagement.model.facility.IRequest;

public class MaintenanceOrder implements IOrder {
	private int orderId;
	private Date orderDate;
	private MOrderStatus status = MOrderStatus.SCHEDULED;
	private double totalCost = 0;
	private List<IOrderDetail> orderDetails = new ArrayList<IOrderDetail>();	
	private IRequest request;

	public MaintenanceOrder(){}
	
	public MaintenanceOrder(int orderId, Date orderDate, MOrderStatus status, List<IOrderDetail> orderDetails,
			IRequest request){
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.status = status;
		this.request = request;
		this.orderDetails = orderDetails;
		calculateTotalCost();
	}

	public double getTotalCost(){
		return totalCost;
	}
	
	public void setRequest(IRequest request){
		this.request = request;
	}
	public IRequest getRequest(){
		return request;
	}
	public void setOrderId(int orderId){
		this.orderId = orderId;
	}
	
	public int getOrderId(){
		return orderId;
	}
	
	public void setOrderDate(Date orderDate){
		this.orderDate = orderDate;
	}
	
	public Date getOrderDate(){
		return orderDate;
	}
	
	public void setStatus(MOrderStatus status){
		this.status = status;
	}
	
	public MOrderStatus getStatus(){
		return status;
	}
	
	public void setOrderDetails(List<IOrderDetail> details){
		this.orderDetails = details;
	}
	

	public List<IOrderDetail> getOrderDetails(){
		return orderDetails;
	}
	

	public void printOrderDetails(){
		System.out.println("Order ID: " + this.orderId + " on  " + this.orderDate);
		System.out.println("Status: " + this.status);
		System.out.println("***********Details***************");
		for(IOrderDetail detail : this.orderDetails){
			System.out.println(detail.getJob().toString() + "\t" + detail.getTotal());
		}
		
	}
	
	public void setTotalCost(double totalCost){
		this.totalCost = totalCost;
	}
	
	public void calculateTotalCost(){
		double total = 0;
		for(IOrderDetail detail : this.orderDetails){
			total += detail.getTotal();
		}
		this.totalCost = total;
	}

}
