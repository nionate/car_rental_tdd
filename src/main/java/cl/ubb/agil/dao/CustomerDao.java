package cl.ubb.agil.dao;

import cl.ubb.agil.model.Customer;

public interface CustomerDao {

	public void create(String rut, String name, String cellPhone, String email, int customerCategory);

	public Customer getCustomer(String cRut);

}
