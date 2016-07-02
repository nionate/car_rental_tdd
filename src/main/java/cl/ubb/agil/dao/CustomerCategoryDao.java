package cl.ubb.agil.dao;

import java.util.List;

import cl.ubb.agil.model.CustomerCategory;

public interface CustomerCategoryDao {

	public List<CustomerCategory> getAllCustomerCategories();

	public CustomerCategory getCustomerCategoryById(int i);

}
