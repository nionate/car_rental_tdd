package cl.ubb.agil.service;

import java.util.List;

import cl.ubb.agil.dao.CustomerCategoryDao;
import cl.ubb.agil.model.CustomerCategory;

public class CustomerCategoryService {

	private CustomerCategoryDao ccDao;
	
	public List<CustomerCategory> getAllCategories() {
		
		return ccDao.getAllCustomerCategories();
	}

}
