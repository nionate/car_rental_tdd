package cl.ubb.agil.model;

public class Promotion {

	private int identifier;
	private String startDate;
	private String endDate;
	private double discount;
	private int [] idCarType;
	
	public Promotion(int identifier, String startDate, String endDate, double discount, int[] idCarType) {
		this.identifier = identifier;
		this.startDate = startDate;
		this.endDate = endDate;
		this.discount = discount;
		this.idCarType = idCarType;
		
	}

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String starDate) {
		this.startDate = starDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int [] getIdCarType() {
		return idCarType;
	}	
	
	public void setIdCarType(int [] idCarType){
		this.idCarType = idCarType;
	}
	
	
}
