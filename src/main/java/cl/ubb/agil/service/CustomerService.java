package cl.ubb.agil.service;

import cl.ubb.agil.dao.CustomerDao;
import cl.ubb.agil.model.Customer;

public class CustomerService {
	
	private CustomerDao cDao;

	public void registerCustomer(Customer customer) {
		
		cDao.registerCustomer(customer.getRut(), customer.getName(), customer.getCellPhone());
	}

}
