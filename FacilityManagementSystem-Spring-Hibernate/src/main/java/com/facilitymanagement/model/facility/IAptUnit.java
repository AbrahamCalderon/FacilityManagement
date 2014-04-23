package com.facilitymanagement.model.facility;

import java.util.List;

public interface IAptUnit extends IUnit {
	
	void setFacId(int facId);
	int getFacId();
	
	void setFutureReservations(List<IReservation> futureReservations);
	List<IReservation> getFutureReservations();
	void addReservation(IReservation newReservation);
	void removeReservation(IReservation remReservation);
	void addDetail(String detail);
	void setDetail(IUnitDetail aptDet);
	IUnitDetail getDetail();
	void getUnitInformation();
	List<IFeature> getFeatures();
	void addFeature(IFeature f);	
	void deleteFeature(IFeature f);
	void setFeatures(List<IFeature> features);
	void setLeases(List<ILease> leases);
	List<ILease> getLeases();
}
