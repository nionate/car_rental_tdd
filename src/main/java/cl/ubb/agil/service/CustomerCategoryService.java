package cl.ubb.agil.service;

import java.util.List;

import cl.ubb.agil.dao.CustomerCategoryDao;
import cl.ubb.agil.model.CustomerCategory;
import cl.ubb.agil.service.exception.EmptyListException;

public class CustomerCategoryService {

	private CustomerCategoryDao ccDao;
	
	public List<CustomerCategory> getAllCategories() throws EmptyListException{
		
		List<CustomerCategory> categories = ccDao.getAllCustomerCategories();
		
		if(categories.isEmpty())
			throw new EmptyListException();
		
		return categories;
	}

}
