package cl.ubb.agil.model;

public class Customer {

	private String rut; 
	private String name;
	
	public Customer(String rut, String name) {
		this.rut = rut;
		this.name = name;
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
	
}
