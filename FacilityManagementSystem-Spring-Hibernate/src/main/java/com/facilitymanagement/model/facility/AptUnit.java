package com.facilitymanagement.model.facility;

import java.util.ArrayList;
import java.util.List;

public class AptUnit implements IAptUnit{
	
	private int unitId;
	private int facId;
	private int capacity;
	private UnitStatus status = UnitStatus.VACANT;
	protected IUnitDetail detail;
	private List<ILease> leases = new ArrayList<ILease>();
	private List<IReservation> futureReservations = new ArrayList<IReservation>();
	private List<IFeature> features = new ArrayList<IFeature>();
	
	public AptUnit(){}
	
	public int getUnitId() {
		return unitId;
	}
	
	public void setUnitId(int unitId){
		this.unitId = unitId;
	}
	
	public int getFacId(){
		return facId;
	}
	public void setFacId(int facId){
		this.facId = facId;
	}
	
	public void setStatus(UnitStatus status){
		this.status = status;
	}
	
	public UnitStatus getStatus(){
		return status;
	}
	

	public void setLeases(List<ILease> leases){
		this.leases = leases;
	}
	
	public List<ILease> getLeases(){
		return leases;
	}

	public void setCapacity(int capacity){
		this.capacity = capacity;
	}
	
	public int getCapacity(){
		return capacity;
	}
		
	public void setFutureReservations(List<IReservation> futureReservations){
		this.futureReservations = futureReservations;
	}
	
	public List<IReservation> getFutureReservations(){
		return futureReservations;
	}
	
	public void addReservation(IReservation newReservation){
		futureReservations.add(newReservation);
	}
	
	public void removeReservation(IReservation remReservation){
		futureReservations.remove(remReservation);
	}
	
	public void getUnitInformation() {
		StringBuilder sb = new StringBuilder();
		sb.append("UNIT DETAILS: \n\n");
		sb.append("Unit ID: " + getUnitId() + "\n");
		sb.append("Status: " + getStatus() + "\n");		
		sb.append("Additional Details: " + getDetail());
		System.out.println(sb);
		System.out.println("\nCurrent Tenants: \n");
		//displayTenants();
		
	}
	
	public void setFeatures(List<IFeature> features){
		this.features = features;
	}
	
	public List<IFeature> getFeatures() {
		return features;
	}
	
	
	public void addFeature(IFeature f){
		features.add(f);
	}
	
	public void deleteFeature(IFeature f){
		features.remove(f);
	}

	public void addDetail(String detail){
		this.detail.addMoreInfo(detail);
	}
	
	public void setDetail(IUnitDetail detail){
		this.detail = detail;
	}
	
	public IUnitDetail getDetail(){
		return detail;
	}

	public StringBuilder printDetails() {
		return this.detail.getDetails();
	}

	
	public double calculateSquareFootage() {
		return this.detail.getSquareFeet();
	}
	
	public String displayUnitId(){
		return "Unit: " + unitId; 
	}
	

}
