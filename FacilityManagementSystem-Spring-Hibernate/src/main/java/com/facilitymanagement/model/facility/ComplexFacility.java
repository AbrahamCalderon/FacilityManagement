package com.facilitymanagement.model.facility;

import java.util.ArrayList;
import java.util.List;

import com.facilitymanagement.model.user.IOwner;

public class ComplexFacility implements IComplexFacility{
	
	private int facilityId;
	private IOwner owner;
	private String facilityName;
	private String street;
	private String city;
	private String state;
	private String zip;
	private FacilityStatus facilityStatus;
	private List<IInspection> inspections = new ArrayList<IInspection>();
	private List<IAptUnit> units = new ArrayList<IAptUnit>();
	private IFacilityDetail detail;
	private List<IFeature> features = new ArrayList<IFeature>();
	
	public ComplexFacility(){}
	
	public ComplexFacility(int facilityId, String facilityName, String street, String city, String state, String zip){
		this.facilityId = facilityId;
		this.facilityName = facilityName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}	
	
	public int getFacilityId() {
		return facilityId;
	}
	
	public void setFacilityId(int facilityId){
		this.facilityId = facilityId;
	}
	
	public void setFacilityName(String facilityName){
		this.facilityName = facilityName;
	}
	public String getFacilityName(){
		return facilityName;
	}
	public void setStreet(String street){
		this.street = street;
	}
	public String getStreet(){
		return street;
	}
	public void setCity(String city){
		this.city = city;
	}
	public String getCity(){
		return city;
	}
	public void setState(String state){
		this.state = state;
	}
	public String getState(){
		return state;
	}
	public void setZip(String zip){
		this.zip = zip;
	}
	public String getZip(){
		return zip;
	}
	public void setDetail(IFacilityDetail detail){
		this.detail = detail;
	}
	
	public IFacilityDetail getDetail(){
		return detail;
	}
	
	public void setOwner(IOwner owner){
		this.owner = owner;
	}
	
	public IOwner getOwner(){
		return owner;
	}	
	
	public void setFacilityStatus(FacilityStatus facilityStatus){
		this.facilityStatus = facilityStatus;
	}
	
	public FacilityStatus getFacilityStatus(){
		return facilityStatus;
	}
	
	public void addDetail(String detail){
		this.detail.addDetails(detail);
	}
	
	public String getUsage(){
		return this.detail.getUsage();
	}
	
	public void setInspections(List<IInspection> inspections){
		this.inspections = inspections;
	}
	
	public List<IInspection> getInspections(){
		return inspections;
	}
	
	public void setUnits(List<IAptUnit> units){
		this.units = units;
	}
	
	public List<IAptUnit> getUnits(){
		return units;
	}

	public int requestAvailableCapacity() {
		int cap = 0;
		for (IAptUnit u: units){
			cap =+ u.getCapacity();
		}
		return cap;
	}

	public void addUnit(IAptUnit newUnit) {
		units.add(newUnit);
		
	}

	public void removeUnit(IAptUnit remUnit) {
		units.remove(remUnit);
	}
	
	public void setFeatures(List<IFeature> features){
		this.features = features;
	}
	
	public List<IFeature> getFeatures(){
		return features;
	}
	
	
	public void getFacilityInformation() {
		StringBuilder sb = new StringBuilder();
		sb.append("Facility Profile: \n\n");
		sb.append("Fac. ID: " + getFacilityId() + "\t");
		sb.append("Name: " + getFacilityName() + "\n\n");
		sb.append("Address: \n" + getStreet() + "\n");
		sb.append(getCity() + ", ");
		sb.append(getState() + " ");
		sb.append(getZip() + "\n");
		System.out.println(sb);
	}


}
