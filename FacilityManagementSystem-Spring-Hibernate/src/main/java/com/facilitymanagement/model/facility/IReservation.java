package com.facilitymanagement.model.facility;

import java.sql.Date;

public interface IReservation {
	void setReservationId(int id);
	int getReservationId();
	void setReservationMadeDate(Date date);
	Date getReservationMadeDate();
	void setCheckInDate(Date checkInDate);
	Date getCheckInDate();
	void setCheckOutDate(Date checkOutDate);
	Date getCheckOutDate();
}
