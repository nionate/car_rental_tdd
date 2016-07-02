package cl.ubb.agil.model;

public class Customer {

	private String rut; 
	private String name;
	private String cellPhone;
	private String email;
	private int idCustomerCategory;
	
	public Customer(String rut, String name, String cellPhone, String email, int idCustomerCategory) {
		this.rut = rut;
		this.name = name;
		this.cellPhone = cellPhone;
		this.email = email;
		this.idCustomerCategory = idCustomerCategory;
	}

	public String getRut() {
		return this.rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public String getCellPhone() {
		return this.cellPhone;
	}
	
	public void setCellPhone(String cellPhone){
		this.cellPhone = cellPhone;
	}

	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}

	public int getIdCustomerCategory() {
		return this.idCustomerCategory;
	}
	
	public void setCustomerCategory(int idCustomerCategory){
		this.idCustomerCategory = idCustomerCategory;
	}
	
}
