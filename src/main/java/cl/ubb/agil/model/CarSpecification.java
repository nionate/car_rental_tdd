package cl.ubb.agil.model;

public class CarSpecification {
	
	private int id;
	private String brand;
	private String model;
	private String year;
	
	
	public CarSpecification(int id, String brand, String model, String year) {
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.year = year;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	
	
	
	
	
	

}
