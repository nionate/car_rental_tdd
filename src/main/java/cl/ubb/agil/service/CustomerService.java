package cl.ubb.agil.service;

import cl.ubb.agil.dao.CustomerDao;
import cl.ubb.agil.model.Customer;
import cl.ubb.agil.model.CustomerCategory;
import cl.ubb.agil.service.exception.ReadErrorException;

public class CustomerService {
	
	private CustomerDao cDao;

	public void registerCustomer(Customer customer) {
		
		cDao.create(customer.getRut(), customer.getName(), customer.getCellPhone(), customer.getEmail(), customer.getCustomerCategory());
	}

	public boolean isRegistered(String cRut) {
		
		Customer customer = cDao.getCustomer(cRut);		
		
		if(customer != null)
			return true;
		return false;
	}

	public CustomerCategory getCustomerCategory(String customerRut) throws ReadErrorException {
		
		Customer customer = cDao.getCustomer(customerRut);
		CustomerCategory cCategory;
		if(customer!=null){
			cCategory = customer.getCustomerCategory();
		}else{
			throw new ReadErrorException();
		}
		
		return cCategory;
	}
	
	
	

}
