package com.facilitymanagement.model.maintenance;

public interface IOrderDetail {
	void setOrderDetailId(int detailId);
	int getOrderDetailId();
	void setJob(IMaintenanceJob job);
	IMaintenanceJob getJob();
	void setQuantity(int quantity);
	int getQuantity();
	void setTaxRate(double rate);
	double getTaxRate();
	double calculateTotal();
	public void setTotal(double total);
	double getTotal();
	void setTerm(OrderTerm term);
	OrderTerm getTerm();
}
