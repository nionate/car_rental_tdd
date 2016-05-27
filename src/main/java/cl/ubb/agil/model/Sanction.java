package cl.ubb.agil.model;

public class Sanction {

	private String identifier;
	private String description;
	private String startDate;
	private int days;
	private Customer customer;
	
	public Sanction(String identifier, String description, String startDate, int days, Customer customer) {
		super();
		this.identifier = identifier;
		this.description = description;
		this.startDate = startDate;
		this.days = days;
		this.customer = customer;
	}
	
	public Sanction() {
	}

	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
	
}
