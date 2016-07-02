package cl.ubb.agil.model;

import java.util.List;

public class Booking {
	
	private String startDate;
	private String endDate;
	private int dueAmount;
	private String customerRut;
	private String licensePlate;
	private List<Branch> branches;
	private List<BookingExtra> bokingExtras;
	
	public Booking(String startDate, String endDate, int dueAmount, String customerRut, String licensePlate,
			List<Branch> branches, List<BookingExtra> bokingExtras) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.dueAmount = dueAmount;
		this.customerRut = customerRut;
		this.licensePlate = licensePlate;
		this.branches = branches;
		this.bokingExtras = bokingExtras;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(int dueAmount) {
		this.dueAmount = dueAmount;
	}

	public String getCustomerRut() {
		return customerRut;
	}

	public void setCustomerRut(String customerRut) {
		this.customerRut = customerRut;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public List<BookingExtra> getBokingExtras() {
		return bokingExtras;
	}

	public void setBokingExtras(List<BookingExtra> bokingExtras) {
		this.bokingExtras = bokingExtras;
	}

}
