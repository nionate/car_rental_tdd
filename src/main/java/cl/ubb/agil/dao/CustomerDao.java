package cl.ubb.agil.dao;

import cl.ubb.agil.model.Customer;
import cl.ubb.agil.model.CustomerCategory;

public interface CustomerDao {

	public void create(String rut, String name, String cellPhone, String email, CustomerCategory customerCategory);

	public Customer getCustomer(String cRut);

}
