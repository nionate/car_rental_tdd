package cl.ubb.agil.model;

public class Branch {
	String identifier;
	String city;
	String sector;
	
	public Branch(String identifier, String city, String sector) {
		super();
		this.identifier = identifier;
		this.city = city;
		this.sector = sector;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	
}
