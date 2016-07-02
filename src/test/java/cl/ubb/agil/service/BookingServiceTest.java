package cl.ubb.agil.service;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import cl.ubb.agil.dao.BookingDao;
import cl.ubb.agil.dao.BranchDao;
import cl.ubb.agil.dao.CarDao;
import cl.ubb.agil.dao.CarSpecificationDao;
import cl.ubb.agil.dao.CarTypeDao;
import cl.ubb.agil.dao.CustomerCategoryDao;
import cl.ubb.agil.dao.CustomerDao;
import cl.ubb.agil.model.BookingExtra;
import cl.ubb.agil.model.Branch;
import cl.ubb.agil.model.Car;
import cl.ubb.agil.model.CarSpecification;
import cl.ubb.agil.model.CarType;
import cl.ubb.agil.model.Customer;
import cl.ubb.agil.model.CustomerCategory;
import cl.ubb.agil.model.Extra;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {
	
	@Mock
	private BookingDao bookingDao;
	@Mock
	private CarTypeDao carTypeDao;
	@Mock
	private CarDao carDao;
	@Mock
	private CarSpecificationDao carSpecDao;
	@Mock
	private CustomerDao customerDao;
	@Mock
	private CustomerCategoryDao cCategoryDao;
	@Mock
	private BranchDao branchDao;
	
	@InjectMocks
	private BookingService bookingService;
	
	/*
	 * El cliente 18770816-8 categoría Persona, reserva el auto con id "1" de la sucursal de Santiago, 
	 * el día 11/06/2016, a las 15:00 hrs y lo entrega en la sucursal de Santiago, 
	 * el día 15/06/2016 a las 15:00 hrs.
	 * Retorna $40.000.
	 */
	@Test
	public void shouldReturn40000WhenAClienteBookingACarForFourDays(){
		
		String rutCliente = "18770816-8";
		String startDay = "11/06/2016";
		String endDay = "15/06/2016";
		String bookingHour = "15:00";
		Branch santiago = new Branch(1, "Santiago", "");
		List<BookingExtra> extras = new ArrayList<>();
		
		//Cars List
		List<Car> cars = new ArrayList<>();
		cars.add(new Car("ASDF-12", "negro", 0));
		cars.add(new Car("QWER-12", "azul", 2));
		
		//Car Specification List
		CarSpecification nissan = new CarSpecification(0, "nissan", "", "");
		CarSpecification chevrolet = new CarSpecification(1, "chevrolet", "", "");
		List<CarSpecification> carSpecs = new ArrayList<>();
		carSpecs.add(nissan);
		carSpecs.add(chevrolet);
		
		CarType type =  new CarType(1, "", "automatic", "diesel", "", 4, 5, 10000);
		CustomerCategory cCategory = new CustomerCategory(1, "Persona");
		Customer customer = new Customer(rutCliente, "", "", "", cCategory.getIdentifier());
		
		when(carTypeDao.getCarType(1)).thenReturn(type);
		when(cCategoryDao.getCustomerCategoryById(1)).thenReturn(cCategory);
		when(customerDao.getCustomer(rutCliente)).thenReturn(customer);
		when(branchDao.get(1)).thenReturn(santiago);
		when(carDao.getAllByBranchId(1)).thenReturn(cars);
		when(carSpecDao.getAllCarsByType(1)).thenReturn(carSpecs);
		when(carSpecDao.get(0)).thenReturn(nissan);
		when(carSpecDao.get(1)).thenReturn(chevrolet);
		
		int resultado = bookingService.booking(rutCliente, santiago.getIdentifier(), startDay, bookingHour, santiago.getIdentifier(), endDay, bookingHour, type.getIdentifier(), extras);
		
		assertEquals(40000, resultado);
	}
	
	/*
	 * El cliente 18770816-8, categoría Persona, 
	 * reserva el auto con id "1" de la sucursal de Santiago, 
	 * el día 11/06/2016 a las 15:00 hrs y lo entrega en la sucursal de Santiago, 
	 * el día 15/06/2016 a las 15:00 hrs, con un extra: una "silla para bebé" con id "1".
	 */
	
	
}
	
	
	
	


