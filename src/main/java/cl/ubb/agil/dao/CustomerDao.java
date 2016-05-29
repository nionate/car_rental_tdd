package cl.ubb.agil.dao;

public interface CustomerDao {

	public void registerCustomer(String rut, String name, String cellPhone, String email, int customerCategory);

}
