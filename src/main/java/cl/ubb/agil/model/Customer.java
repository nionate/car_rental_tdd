package cl.ubb.agil.model;

public class Customer {

	private String rut; 
	private String name;
	private String cellPhone;
	
	public Customer(String rut, String name, String cellPhone) {
		this.rut = rut;
		this.name = name;
		this.cellPhone = cellPhone;
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
	
}
