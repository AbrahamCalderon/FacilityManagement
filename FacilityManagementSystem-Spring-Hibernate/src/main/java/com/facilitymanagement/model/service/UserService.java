package com.facilitymanagement.model.service;

import com.facilitymanagement.dal.TenantHibernateDAO;
import com.facilitymanagement.dal.UserHibernateDAO;
import com.facilitymanagement.model.user.IOwner;
import com.facilitymanagement.model.user.ITenant;

public class UserService {

	UserHibernateDAO userDAO = new UserHibernateDAO();
	TenantHibernateDAO tenantDAO = new TenantHibernateDAO();
	

	/***********************************************/
	/*************** OWNER *************************/
	public void addOwner(IOwner owner){
		userDAO.insertNewOwner(owner);
	}
	
	public void updateOwner(IOwner owner){
		userDAO.updateOwner(owner);
	}
	
	public void deleteOwner(IOwner owner){
		userDAO.deleteOwner(owner);
	}
	
	public IOwner retrieveOwnerById (int ownerId){
		return userDAO.getOwnerById(ownerId);
	}
	
	
	/***********************************************/
	/***************** TENANT **********************/
	public void addTenant(ITenant tenant){
		tenantDAO.insertTenant(tenant);
	}
	
	public void updateTenant(ITenant tenant){
		tenantDAO.updateTenant(tenant);
	}
	
	public void deleteTenant(ITenant tenant){
		tenantDAO.deleteTenant(tenant);
	}
	
	public ITenant retrieveTenantById(int tenantId){
		return tenantDAO.getTenantById(tenantId);
	}
}
