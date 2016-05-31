package cl.ubb.agil.service;

import cl.ubb.agil.dao.CustomerDao;
import cl.ubb.agil.model.Customer;
import cl.ubb.agil.model.CustomerCategory;
import cl.ubb.agil.service.exception.CreateException;
import cl.ubb.agil.service.exception.ReadErrorException;

public class CustomerService {

	private CustomerDao cDao;

	public void registerCustomer(Customer customer) throws CreateException {

		if (!validateRut(customer.getRut()))
			throw new CreateException();
		if(!validateEmail(customer.getEmail()))
			throw new CreateException();
		if(!validateCellPhone(customer.getCellPhone()))
			throw new CreateException();
		if(customer.getCustomerCategory() == null)
			throw new CreateException();
		
		cDao.create(customer);
	}


	public boolean isRegistered(String cRut) {

		Customer customer = cDao.getCustomer(cRut);

		if (customer != null)
			return true;
		return false;
	}

	public CustomerCategory getCustomerCategory(String customerRut) throws ReadErrorException {

		Customer customer = cDao.getCustomer(customerRut);
		CustomerCategory cCategory;
		if (customer != null) {
			cCategory = customer.getCustomerCategory();
		} else {
			throw new ReadErrorException();
		}

		return cCategory;
	}

	/* private methods */
	private boolean validateRut(String customerRut) {
		boolean validation = false;
		customerRut = customerRut.toUpperCase();
		customerRut = customerRut.replace(".", "");
		customerRut = customerRut.replace("-", "");
		int rutAux = Integer.parseInt(customerRut.substring(0, customerRut.length() - 1));

		char dv = customerRut.charAt(customerRut.length() - 1);

		int m = 0, s = 1;
		for (; rutAux != 0; rutAux /= 10) {
			s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
		}
		if (dv == (char) (s != 0 ? s + 47 : 75)) {
			validation = true;
		}
		return validation;
	}
	
	private boolean validateEmail(String email) {
		
		if(email.contains("@") && (email.contains(".com") || email.contains(".cl")))
			return true;
		return false;
	}
	
	private boolean validateCellPhone(String cellPhone){
		
		if(cellPhone.length() != 8)
			return false;
		return true;
	}

}
