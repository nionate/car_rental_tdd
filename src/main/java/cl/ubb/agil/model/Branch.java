package cl.ubb.agil.model;

public class Branch {
	int identifier;
	String city;
	String sector;
	
	public Branch(int identifier, String city, String sector) {
		super();
		this.identifier = identifier;
		this.city = city;
		this.sector = sector;
	}
	
	public int getIdentifier() {
		return identifier;
	}
	public void setIdentifier(int identifier) {
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
