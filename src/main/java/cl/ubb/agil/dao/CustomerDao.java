package cl.ubb.agil.dao;

import cl.ubb.agil.model.Customer;
import cl.ubb.agil.model.CustomerCategory;

public interface CustomerDao {

	public void create(Customer customer);

	public Customer getCustomer(String cRut);

}
