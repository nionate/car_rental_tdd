package cl.ubb.agil.model;

public class TimeConstraint {
	
	private int minNumberOfDays;
	private int maxNumberOfDays;
	
	public TimeConstraint(){}
	

	public TimeConstraint(int minNumberOfDays, int maxNumberOfDays) {
		this.minNumberOfDays = minNumberOfDays;
		this.maxNumberOfDays = maxNumberOfDays;
	}


	public int getMinNumberOfDays() {
		return this.minNumberOfDays;
	}

	public void setMinNumberOfDays(int i) throws NumberOfDaysException {
		if(i < 0)
			throw new NumberOfDaysException();
		if(maxNumberOfDays != 0 && i > maxNumberOfDays)
			throw new NumberOfDaysException();
		this.minNumberOfDays = i;
	}

	public int getMaxNumberOfDays() {
		return maxNumberOfDays;
	}

	public void setMaxNumberOfDays(int i) throws NumberOfDaysException {
		if(i < 0)
			throw new NumberOfDaysException();
		if(minNumberOfDays != 0 && i < minNumberOfDays)
			throw new NumberOfDaysException();
		this.maxNumberOfDays = i;
	}
	
}
