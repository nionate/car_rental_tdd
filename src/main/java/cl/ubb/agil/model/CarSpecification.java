package cl.ubb.agil.model;

public class CarSpecification {

	private int identifier;
	private String brand;
	private String model;
	private int year;
	
	public CarSpecification(int identifier, String brand, String model, int year) {
		this.identifier = identifier;
		this.brand = brand;
		this.model = model;
		this.year = year;
	}

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
	
}
