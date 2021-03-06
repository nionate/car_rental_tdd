package cl.ubb.agil.service;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import cl.ubb.agil.dao.BookingDao;
import cl.ubb.agil.dao.BranchDao;
import cl.ubb.agil.dao.CarDao;
import cl.ubb.agil.dao.CarSpecificationDao;
import cl.ubb.agil.dao.CarTypeDao;
import cl.ubb.agil.dao.CustomerCategoryDao;
import cl.ubb.agil.dao.CustomerDao;
import cl.ubb.agil.dao.ExtraDao;
import cl.ubb.agil.dao.SanctionDao;
import cl.ubb.agil.dao.TimeConstraintDao;
import cl.ubb.agil.model.Booking;
import cl.ubb.agil.model.BookingExtra;
import cl.ubb.agil.model.Branch;
import cl.ubb.agil.model.Car;
import cl.ubb.agil.model.CarSpecification;
import cl.ubb.agil.model.CarType;
import cl.ubb.agil.model.Customer;
import cl.ubb.agil.model.CustomerCategory;
import cl.ubb.agil.model.Extra;
import cl.ubb.agil.model.Sanction;
import cl.ubb.agil.model.TimeConstraint;
import cl.ubb.agil.service.exception.CreateException;
import cl.ubb.agil.service.exception.EmptyListException;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest{

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
	@Mock
	private ExtraDao extraDao;
	@Mock
	private SanctionDao sanctionDao;

	@InjectMocks
	private BookingService bookingService;

	/*
	 * El cliente 18770816-8 categoría Persona, reserva el auto con id "1" de
	 * la sucursal de Santiago, el día 11/06/2016, a las 15:00 hrs y lo entrega
	 * en la sucursal de Santiago, el día 15/06/2016 a las 15:00 hrs. Retorna
	 * $40.000.
	 */
	@Test
	public void shouldReturn40000WhenAClienteBookingACarForFourDays() throws ParseException, CreateException{

		String rutCliente = "18770816-8";
		String startDay = "11/06/2016";
		String endDay = "15/06/2016";
		String bookingHour = "15:00";
		Branch santiago = new Branch("1", "Santiago", "");
		List<BookingExtra> extras = new ArrayList<>();

		// Cars List
		List<Car> cars = new ArrayList<>();
		cars.add(new Car("ASDF-12", "negro", 0));
		cars.add(new Car("QWER-12", "azul", 2));

		// Car Specification List
		CarSpecification nissan = new CarSpecification(0, "nissan", "", "");
		CarSpecification chevrolet = new CarSpecification(1, "chevrolet", "", "");
		List<CarSpecification> carSpecs = new ArrayList<>();
		carSpecs.add(nissan);
		carSpecs.add(chevrolet);

		CarType type = new CarType(1, "", "automatic", "diesel", "", 4, 5, 10000);
		CustomerCategory cCategory = new CustomerCategory(1, "Persona");
		Customer customer = new Customer(rutCliente, "", "", "", cCategory.getIdentifier());

		when(carTypeDao.getCarType(1)).thenReturn(type);
		when(cCategoryDao.getCustomerCategoryById(1)).thenReturn(cCategory);
		when(customerDao.getCustomer(rutCliente)).thenReturn(customer);
		when(branchDao.get("1")).thenReturn(santiago);
		when(carDao.getAllByBranchId("1")).thenReturn(cars);
		when(carSpecDao.getAllCarsByType(1)).thenReturn(carSpecs);
		when(carSpecDao.get(0)).thenReturn(nissan);
		when(carSpecDao.get(1)).thenReturn(chevrolet);

		int resultado = bookingService.booking(rutCliente, santiago.getIdentifier(), startDay, bookingHour,
				santiago.getIdentifier(), endDay, bookingHour, type.getIdentifier(), extras);

		assertEquals(40000, resultado);
	}

	/*
	 * El cliente 18770816-8, categoría Persona, reserva el auto con id "1" de
	 * la sucursal de Santiago, el día 11/06/2016 a las 15:00 hrs y lo entrega
	 * en la sucursal de Santiago, el día 15/06/2016 a las 15:00 hrs, con un
	 * extra: una "silla para bebé" con id "1". Retorna $60.000($10.000 auto +
	 * $5.000 extra).
	 */
	@Test
	public void shoulReturn60000WhenAClientBookingACarForFourDaysWithExtras() throws ParseException, CreateException{

		String rutCliente = "18770816-8";
		String startDay = "11/06/2016";
		String endDay = "15/06/2016";
		String bookingHour = "15:00";
		Branch santiago = new Branch("1", "Santiago", "");
		Extra sillaBebe = new Extra(1, "silla para bebe", "", 5000);
		List<BookingExtra> extras = new ArrayList<>();
		extras.add(new BookingExtra(1, 1));

		// Cars List
		List<Car> cars = new ArrayList<>();
		cars.add(new Car("ASDF-12", "negro", 0));
		cars.add(new Car("QWER-12", "azul", 2));

		// Car Specification List
		CarSpecification nissan = new CarSpecification(0, "nissan", "", "");
		CarSpecification chevrolet = new CarSpecification(1, "chevrolet", "", "");
		List<CarSpecification> carSpecs = new ArrayList<>();
		carSpecs.add(nissan);
		carSpecs.add(chevrolet);

		CarType type = new CarType(1, "", "automatic", "diesel", "", 4, 5, 10000);
		CustomerCategory cCategory = new CustomerCategory(1, "Persona");
		Customer customer = new Customer(rutCliente, "", "", "", cCategory.getIdentifier());

		when(carTypeDao.getCarType(1)).thenReturn(type);
		when(cCategoryDao.getCustomerCategoryById(1)).thenReturn(cCategory);
		when(customerDao.getCustomer(rutCliente)).thenReturn(customer);
		when(branchDao.get("1")).thenReturn(santiago);
		when(carDao.getAllByBranchId("1")).thenReturn(cars);
		when(carSpecDao.getAllCarsByType(1)).thenReturn(carSpecs);
		when(carSpecDao.get(0)).thenReturn(nissan);
		when(carSpecDao.get(1)).thenReturn(chevrolet);
		when(extraDao.get(1)).thenReturn(sillaBebe);

		int resultado = bookingService.booking(rutCliente, santiago.getIdentifier(), startDay, bookingHour,
				santiago.getIdentifier(), endDay, bookingHour, type.getIdentifier(), extras);

		assertEquals(60000, resultado);
	}

	/*
	 * El cliente 18431210-7, categor�a Persona, reserva el auto con id "3" de
	 * la sucursal de Santiago, el d�a 02/06/2016 a las 16:20 hrs y lo entrega
	 * en la sucursal de Santiago el d�a 04/06/2016 a las 16:20 hrs, m�s dos
	 * extras: una "silla para beb�" con id "1" y un GPS con id "2". Retorna
	 * $72.000 ($10.000 auto + $5.000 silla para beb� + $3.000 GPS).
	 */
	@Test
	public void shouldReturn72000WhenAClientBookingACarForTwoDaysWithTwoExtras() throws ParseException, CreateException{

		String rutCliente = "18770816-8";
		String startDay = "11/06/2016";
		String endDay = "15/06/2016";
		String bookingHour = "15:00";
		Branch santiago = new Branch("1", "Santiago", "");
		Extra sillaBebe = new Extra(1, "silla para bebe", "", 5000);
		Extra gps = new Extra(2, "GPS", "", 3000);
		List<BookingExtra> extras = new ArrayList<>();
		extras.add(new BookingExtra(1, 1));
		extras.add(new BookingExtra(1, 2));

		// Cars List
		List<Car> cars = new ArrayList<>();
		cars.add(new Car("ASDF-12", "negro", 0));
		cars.add(new Car("QWER-12", "azul", 2));

		// Car Specification List
		CarSpecification nissan = new CarSpecification(0, "nissan", "", "");
		CarSpecification chevrolet = new CarSpecification(1, "chevrolet", "", "");
		List<CarSpecification> carSpecs = new ArrayList<>();
		carSpecs.add(nissan);
		carSpecs.add(chevrolet);

		CarType type = new CarType(1, "", "automatic", "diesel", "", 4, 5, 10000);
		CustomerCategory cCategory = new CustomerCategory(1, "Persona");
		Customer customer = new Customer(rutCliente, "", "", "", cCategory.getIdentifier());

		when(carTypeDao.getCarType(1)).thenReturn(type);
		when(cCategoryDao.getCustomerCategoryById(1)).thenReturn(cCategory);
		when(customerDao.getCustomer(rutCliente)).thenReturn(customer);
		when(branchDao.get("1")).thenReturn(santiago);
		when(carDao.getAllByBranchId("1")).thenReturn(cars);
		when(carSpecDao.getAllCarsByType(1)).thenReturn(carSpecs);
		when(carSpecDao.get(0)).thenReturn(nissan);
		when(carSpecDao.get(1)).thenReturn(chevrolet);
		when(extraDao.get(1)).thenReturn(sillaBebe);
		when(extraDao.get(2)).thenReturn(gps);

		int resultado = bookingService.booking(rutCliente, santiago.getIdentifier(), startDay, bookingHour,
				santiago.getIdentifier(), endDay, bookingHour, type.getIdentifier(), extras);

		assertEquals(72000, resultado);
	}
	
	@Test(expected = CreateException.class)
	public void shouldReturnAnExceptionWhenCustomerHaveAnActiveSanction() throws ParseException, CreateException{
		String rutCliente = "18770816-8";
		String startDay = "11/06/2016";
		String endDay = "15/06/2016";

		List<Sanction> sanctions = new ArrayList<>();
		sanctions.add(new Sanction("1", "", "10/06/2016", 5, rutCliente));
		when(sanctionDao.getAllByCostumer("18770816-8")).thenReturn(sanctions);

		int resultado = bookingService.booking(rutCliente,"", startDay, "",
				"", endDay, "", 1, null);

	}
	
	
	/*El cliente 18431210-7, tiene dos reservas, una con fecha 11/10/2015 y otra con fecha 15/11/2015. La fecha de inicio para listar reservas es : 10/10/2015.
	 *Retorna una lista con dos reservas.*/
	@Test
	public void shouldReturnListWithTwoBookingsWhenTheCustomerHasTwoBookingsfromASpecificDateTest() throws EmptyListException, ParseException{
		String rutCustomer = "18431210-7";
		String startRangeDate = "10/10/2015";
		List <Booking> bookingsbyRangeDateAndCustomer = new ArrayList<Booking>();
		List <Booking> bookings = new ArrayList<Booking>();
		Booking booking1 = new Booking("11/10/2015","17/10/2015",55000, "18431210-7", "",null, null);
		Booking booking2 = new Booking("15/11/2015","21/11/2015",60000, "18431210-7", "",null, null);
		bookings.add(booking1);
		bookings.add(booking2);
		
		when(bookingDao.getAllBookingByCostumer(rutCustomer)).thenReturn(bookings);
		
		bookingsbyRangeDateAndCustomer = bookingService.getBookingsByRangeDateAndCustomer(rutCustomer,startRangeDate,"");
		
		assertEquals(2,bookingsbyRangeDateAndCustomer.size());
		assertEquals("18431210-7",bookingsbyRangeDateAndCustomer.get(0).getCustomerRut());
		assertEquals("18431210-7",bookingsbyRangeDateAndCustomer.get(1).getCustomerRut());
		assertEquals(55000,bookingsbyRangeDateAndCustomer.get(0).getDueAmount());
		assertEquals(60000,bookingsbyRangeDateAndCustomer.get(1).getDueAmount());
	}
	
	/*El cliente 18431210-7, no tiene ninguna reserva. La fecha de inicio para listar reservas es: 10/10/2015.
	 *Retorna EmptyListException.*/
	@Test(expected = EmptyListException.class)
	public void GetBookingsbyRangeDateAndCustomerReturnEmptyTest() throws EmptyListException, ParseException {
		String rutCustomer = "18431210-7";
		String startDate = "10/10/2015";
		List<Booking> bookings = new ArrayList<Booking>();
		
		when(bookingDao.getAllBookingByCostumer(rutCustomer)).thenReturn(bookings);
		
		bookingService.getBookingsByRangeDateAndCustomer(rutCustomer, startDate, "");
	}
	
	/*El cliente 18431210-7 , tiene cuatro reservas, la primera con fecha 11/09/2015, la segunda con fecha 11/10/2015, la tercera con fecha 15/11/2015 y la cuarta con fecha 17/12/2015. La fecha de inicio para listar reservas es: 10/10/2015.
	 *Retorna una lista con tres reservas*/
	@Test
	public void shouldReturnListWithTreeBookingsWhenTheCustomerHasThreeBookingfromASpecificDateTest() throws EmptyListException, ParseException {
		String rutCustomer = "18431210-7";
		String startRangeDate = "10/10/2015";
		List <Booking> bookingsbyRangeDateAndCustomer = new ArrayList<Booking>();
		List <Booking> bookings = new ArrayList<Booking>();
		Booking booking1 = new Booking("11/09/2015","17/09/2015",55000, "18431210-7", "",null, null);
		Booking booking2 = new Booking("11/10/2015","19/10/2015",60000, "18431210-7", "",null, null);
		Booking booking3 = new Booking("15/11/2015","21/11/2015",50000, "18431210-7", "",null, null);
		Booking booking4 = new Booking("17/12/2015","25/12/2015",70000, "18431210-7", "",null, null);
		bookings.add(booking1);
		bookings.add(booking2);
		bookings.add(booking3);
		bookings.add(booking4);
		
		when(bookingDao.getAllBookingByCostumer(rutCustomer)).thenReturn(bookings);
		
		bookingsbyRangeDateAndCustomer = bookingService.getBookingsByRangeDateAndCustomer(rutCustomer,startRangeDate,"");
		
		assertEquals(3,bookingsbyRangeDateAndCustomer.size());
		assertEquals("18431210-7",bookingsbyRangeDateAndCustomer.get(0).getCustomerRut());
		assertEquals("18431210-7",bookingsbyRangeDateAndCustomer.get(1).getCustomerRut());
		assertEquals("18431210-7",bookingsbyRangeDateAndCustomer.get(2).getCustomerRut());
		assertEquals(60000,bookingsbyRangeDateAndCustomer.get(0).getDueAmount());
		assertEquals(50000,bookingsbyRangeDateAndCustomer.get(1).getDueAmount());
		assertEquals(70000,bookingsbyRangeDateAndCustomer.get(2).getDueAmount());
	}
	
	/*El cliente 18431210-7 , tiene tres reservas, la primera con fecha de inicio: 11/09/2015 y fecha de termino: 17/09/2015, 
	 * la segunda con fecha de inicio: 11/10/2015 y con fecha de termino: 19/10/2015 y 
	 * la tercera con fecha de inicio: 10/11/2015 y con fecha de termino: 19/11/2015. El rango de fechas para listar reservas es: 10/10/2015 y 20/10/2015.
	 * Retorna una lista con una reserva
	 */
	@Test
	public void shouldReturnListWhenTheCustomerHasOneBookingfromASpecificStartAndEndDateTest() throws EmptyListException, ParseException{
		String rutCustomer = "18431210-7";
		String startRangeDate = "10/10/2015";
		String endRangeDate = "20/10/2015";
		List <Booking> bookingsbyRangeDateAndCustomer = new ArrayList<Booking>();
		List <Booking> bookings = new ArrayList<Booking>();
		Booking booking1 = new Booking("11/09/2015","17/09/2015",70000, "18431210-7", "",null, null);
		Booking booking2 = new Booking("11/10/2015","21/10/2015",80000, "18431210-7", "",null, null);
		Booking booking3 = new Booking("10/11/2015","19/11/2015",90000, "18431210-7", "",null, null);
		bookings.add(booking1);
		bookings.add(booking2);
		bookings.add(booking3);
		
		when(bookingDao.getAllBookingByCostumer(rutCustomer)).thenReturn(bookings);
		
		bookingsbyRangeDateAndCustomer = bookingService.getBookingsByRangeDateAndCustomer(rutCustomer,startRangeDate,endRangeDate);
		
		assertEquals(1,bookingsbyRangeDateAndCustomer.size());
		assertEquals("18431210-7",bookingsbyRangeDateAndCustomer.get(0).getCustomerRut());
		assertEquals(80000,bookingsbyRangeDateAndCustomer.get(0).getDueAmount());
	}

}
