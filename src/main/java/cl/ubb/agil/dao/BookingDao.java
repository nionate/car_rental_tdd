package cl.ubb.agil.dao;

import java.util.List;

import cl.ubb.agil.model.Booking;

public interface BookingDao {
	
	public void create(Booking booking);

	public List<Booking> getAllBookingByCostumer(String rutCustomer);

}
