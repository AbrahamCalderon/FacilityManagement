package com.facilitymanagement.model.maintenance;

import java.sql.Date;
import java.util.List;

import com.facilitymanagement.model.facility.IRequest;

public interface IOrder {
	double getTotalCost();
	void setRequest(IRequest request);
	IRequest getRequest();
	void setOrderId(int orderId);
	int getOrderId();
	void setOrderDate(Date orderDate);
	Date getOrderDate();
	void setStatus(MOrderStatus status);
	MOrderStatus getStatus();
	void setOrderDetails(List<IOrderDetail> details);
	List<IOrderDetail> getOrderDetails();
	void printOrderDetails();
	void setTotalCost(double totalCost);
	void calculateTotalCost();
}
