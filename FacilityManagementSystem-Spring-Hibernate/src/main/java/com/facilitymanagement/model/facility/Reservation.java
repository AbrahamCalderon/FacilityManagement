package com.facilitymanagement.model.facility;

import java.sql.Date;

public class Reservation implements IReservation {
	private int reservationId;
	private Date reservationMadeDate;
	private Date checkInDate;
	private Date checkOutDate;
	
	public Reservation(){}
	public Reservation (Date reservationMadeDate, Date checkInDate, Date checkOutDate){
		this.reservationMadeDate = reservationMadeDate;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}
	
	public void setReservationId(int reservationId){
		this.reservationId = reservationId;
	}
	
	public int getReservationId(){
		return reservationId;
	}
	
	public void setReservationMadeDate(Date reservationMadeDate){
		this.reservationMadeDate = reservationMadeDate;
	}
	public Date getReservationMadeDate(){
		return reservationMadeDate;
	}
	public void setCheckInDate(Date checkInDate){
		this.checkInDate = checkInDate;
	}
	public Date getCheckInDate(){
		return checkInDate;
	}
	public void setCheckOutDate(Date checkOutDate){
		this.checkOutDate = checkOutDate;
	}
	public Date getCheckOutDate(){
		return checkOutDate;
	}
	
	public String toString(){
		return "RESERVATION DETAILS" +
				"\nDate of reservation request: " + reservationMadeDate +
				"\nScheduled Check-in Date: " + checkInDate +
				"\nScheduled Check-out Date: " + checkOutDate;
	}
}
