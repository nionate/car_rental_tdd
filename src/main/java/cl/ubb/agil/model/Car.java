package cl.ubb.agil.model;

public class Car {
	
	private String licensePlate;
	private String color;
	private int idCarSpecification;
	
	public Car(String licensePlate, String color, int idCarSpecification) {
		this.licensePlate = licensePlate;
		this.color = color;
		this.idCarSpecification = idCarSpecification;
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
	
	public int getIdCarSpecification(){
		return this.idCarSpecification;
	}

	public void setIdCardSpecification(int idCardSpecification){
		this.idCarSpecification = idCardSpecification;
	}

}
