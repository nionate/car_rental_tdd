package cl.ubb.agil.model;

public class CarType {
	
	private int identifier;
	private String name;
	private String transmisionType;
	private String fuelType;
	private String airConditioning;
	private int bags;
	private int passengers;
	private int dailyPrice;
	private String idTimeConstraint;
	
	public CarType(int identifier, String name, String transmisionType, String fuelType, String airConditiong, int bags, int passengers, int dailyPrice, String idTimeConstraint) {
		this.identifier = identifier;
		this.name = name;
		this.transmisionType = transmisionType;
		this.fuelType = fuelType;
		this.airConditioning = airConditiong;
		this.bags = bags;
		this.passengers = passengers;
		this.dailyPrice = dailyPrice;
		this.idTimeConstraint = idTimeConstraint;
	}

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTransmisionType() {
		return transmisionType;
	}

	public void setTransmisionType(String transmisionType) {
		this.transmisionType = transmisionType;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	

	public String getAirConditioning() {
		return airConditioning;
	}

	public void setAirConditioning(String airConditioning) {
		this.airConditioning = airConditioning;
	}

	public int getBags() {
		return bags;
	}

	public void setBags(int bags) {
		this.bags = bags;
	}

	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	public int getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(int dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

	public String getIdTimeConstraint() {
		return idTimeConstraint;
	}

	public void setIdTimeConstraint(String idTimeConstraint) {
		this.idTimeConstraint = idTimeConstraint;
	}
	
	

	
}