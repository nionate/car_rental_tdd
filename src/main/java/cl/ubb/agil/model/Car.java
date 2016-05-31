package cl.ubb.agil.model;

public class Car {
	
	private String licensePlate;
	private String color;
	
	public Car(String licensePlate, String color) {
		this.licensePlate = licensePlate;
		this.color = color;
	
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	
	
	

}
