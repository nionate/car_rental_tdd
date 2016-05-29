package cl.ubb.agil.model;

public class Customer {

	private String rut; 
	private String name;
	private String cellPhone;
	private String email;
	private int customerCategoryIdentifier;
	
	public Customer(String rut, String name, String cellPhone, String email, int customerCategoryIdentifier) {
		this.rut = rut;
		this.name = name;
		this.cellPhone = cellPhone;
		this.email = email;
		this.customerCategoryIdentifier = customerCategoryIdentifier;
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

	public int getCustomerCategoryIdentifier() {
		return this.customerCategoryIdentifier;
	}
	
	public void setCustomerCategoryIdentifier(int customerCategoryIdentifier){
		this.customerCategoryIdentifier = customerCategoryIdentifier;
	}
	
}
