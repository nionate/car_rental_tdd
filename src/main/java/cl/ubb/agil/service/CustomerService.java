package cl.ubb.agil.service;

import cl.ubb.agil.dao.CustomerDao;
import cl.ubb.agil.model.Customer;

public class CustomerService {
	
	private CustomerDao cDao;

	public void registerCustomer(Customer customer) {
		
		cDao.create(customer.getRut(), customer.getName(), customer.getCellPhone(), customer.getEmail(), customer.getCustomerCategoryIdentifier());
	}

	public boolean isRegistered(String cRut) {
		
		Customer customer = cDao.getCustomer(cRut);
		
		
		if(customer != null)
			return true;
		return false;
	}

}
