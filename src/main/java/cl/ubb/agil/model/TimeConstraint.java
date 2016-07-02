package cl.ubb.agil.model;

public class TimeConstraint {
	private int minNumberOfDays;
	private int maxNumberOfDays;
	private int identifierCarType;
	private int identifierCustomerCategory;
	
	
	public TimeConstraint(int minNumberOfDays, int maxNumberOfDays, int identifierCarType,
			int identifierCustomerCategory) {
		this.minNumberOfDays = minNumberOfDays;
		this.maxNumberOfDays = maxNumberOfDays;
		this.identifierCarType = identifierCarType;
		this.identifierCustomerCategory = identifierCustomerCategory;
	}
	
	public int getMinNumberOfDays() {
		return minNumberOfDays;
	}
	public void setMinNumberOfDays(int minNumberOfDays) {
		this.minNumberOfDays = minNumberOfDays;
	}
	public int getMaxNumberOfDays() {
		return maxNumberOfDays;
	}
	public void setMaxNumberOfDays(int maxNumberOfDays) {
		this.maxNumberOfDays = maxNumberOfDays;
	}
	public int getIdentifierCarType() {
		return identifierCarType;
	}
	public void setIdentifierCarType(int identifierCarType) {
		this.identifierCarType = identifierCarType;
	}
	public int getIdentifierCustomerCategory() {
		return identifierCustomerCategory;
	}
	public void setIdentifierCustomerCategory(int identifierCustomerCategory) {
		this.identifierCustomerCategory = identifierCustomerCategory;
	}
}	
	