package com.facilitymanagement.model.facility;

import java.util.ArrayList;
import java.util.List;

public class AptUnitDetail implements IUnitDetail{

	private int aptDetId;
	private int aptId;
	private double length;
	private double width;
	private int numRooms;
	private double numBaths;
	private String moreDetail = null;
	
	
	public AptUnitDetail(double length, double width, int numRooms, double numBaths){
		this.length = length;
		this.width = width;
		this.numRooms = numRooms;
		this.numBaths = numBaths;
		
	}
	
	public void setAptDetId(int id){
		this.aptDetId = id;
	}
	
	public int getAptDetId(){
		return aptDetId;
	}
	
	public void setAptId(int aptId){
		this.aptId = aptId;
	}
	
	public int getAptId(){
		return aptId;
	}
	
	public AptUnitDetail() {}

	public void setLength(double length){
		this.length = length;
	}
	public double getLength(){
		return length;
	}

	public void setWidth(double width){
		this.width = width;
	}
	public double getWidth(){
		return width;
	}
	public void setNumRooms(int numRooms){
		this.numRooms = numRooms;
	}
	public int getNumRooms(){
		return numRooms;
	}
	public void setNumBaths(double numBaths){
		this.numBaths = numBaths;
	}
	public double getNumBaths(){
		return numBaths;
	}
	
	public void setMoreDetail(String moreDetail){
		this.moreDetail = moreDetail;
	}
	public String getMoreDetail(){
		return moreDetail;
	}
	
	public void addMoreInfo(String moreDetail) {
		this.moreDetail += moreDetail;
	}
	
	public double getSquareFeet(){
		return length * width;
	}
	
	public StringBuilder getDetails() {
		StringBuilder sb = new StringBuilder();
		sb.append("Beds: " + numRooms);
		sb.append("\nBaths: " + numBaths);
		sb.append("\nSquare Footage: " + getSquareFeet());
		sb.append("\n\nAdditional details: " + moreDetail);
		
		return sb;
	}

}
