package cl.ubb.agil.model;

public class Extra {
	int identifier;
	String name;
	String description;
	int dailyPrice;
	
	public Extra(int identifier, String name, String description, int dailyPrice) {
		super();
		this.identifier = identifier;
		this.name = name;
		this.description = description;
		this.dailyPrice = dailyPrice;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(int dailyPrice) {
		this.dailyPrice = dailyPrice;
	}
}
