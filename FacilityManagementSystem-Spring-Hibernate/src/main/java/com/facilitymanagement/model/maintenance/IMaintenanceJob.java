package com.facilitymanagement.model.maintenance;

public interface IMaintenanceJob {
	void setJobId(int jobId);
	int getJobId();
	void setJobDescription(String description);
	String getJobDescription();
	void setJobPerformer(String jobPerformer);
	String getJobPerformer();
	void setPrice(double price);
	double getPrice();
	void setDetailId(int detailId);
	int getDetailId();
}
