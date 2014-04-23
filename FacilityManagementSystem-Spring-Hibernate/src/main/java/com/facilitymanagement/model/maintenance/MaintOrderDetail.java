package com.facilitymanagement.model.maintenance;

public class MaintOrderDetail implements IOrderDetail {
	private int orderDetailId;
	private IMaintenanceJob job;
	private int quantity;
	private double taxRate = 0.15;
	private double total;
	private OrderTerm term;
	
	public void setOrderDetailId(int detailId){
		this.orderDetailId = detailId;
	}
	
	public int getOrderDetailId(){
		return orderDetailId;
	}

	public void setJob(IMaintenanceJob job){
		this.job = job;
	}
	
	public IMaintenanceJob getJob(){
		return job;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public void setTaxRate(double taxRate){
		this.taxRate = taxRate;
	}
	
	public double getTaxRate(){
		return taxRate;
	}
	
	public void setTotal(double total){
		this.total = total;
	}
	
	public double getTotal(){
		return calculateTotal();
	}
	
	public double calculateTotal(){
		total = this.job.getPrice() * quantity;
		total += total * taxRate;
		return total;
	}
	
	public void setTerm(OrderTerm term){
		this.term = term;
	}
	
	public OrderTerm getTerm(){
		return term;
	}
}
