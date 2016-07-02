package cl.ubb.agil.model;

public class BookingExtra {
	
	private int number;
	private int extraId;
	
	public BookingExtra(int number, int extraId) {
		super();
		this.number = number;
		this.extraId = extraId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getExtraId() {
		return extraId;
	}

	public void setExtraId(int extraId) {
		this.extraId = extraId;
	}
	
	

}
