package cl.ubb.agil.model;

public class TimeConstraint {
	private String idTimeConstraint;
	private int minNumberOfDays;
	private int maxNumberOfDays;
	
	
	public TimeConstraint(int minNumberOfDays, int maxNumberOfDays, String idTimeConstraint) {
		this.minNumberOfDays = minNumberOfDays;
		this.maxNumberOfDays = maxNumberOfDays;
		this.idTimeConstraint = idTimeConstraint;
		
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

	public String getIdTimeConstraint() {
		return idTimeConstraint;
	}

	public void setIdTimeConstraint(String idTimeConstraint) {
		this.idTimeConstraint = idTimeConstraint;
	}
	
	

	
	
	
	
}
