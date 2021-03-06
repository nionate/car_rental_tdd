package cl.ubb.agil.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import cl.ubb.agil.model.Customer;
import cl.ubb.agil.model.CustomerCategory;
import cl.ubb.agil.model.Extra;
import cl.ubb.agil.service.exception.CreateException;
import cl.ubb.agil.service.exception.EmptyListException;

public class BookingService {
	
	private BookingDao bookingDao;
	private CarTypeDao carTypeDao;
	private CustomerDao customerDao;
	private CustomerCategoryDao cCategoryDao;
	private CarDao carDao;
	private CarSpecificationDao carSpecDao;
	private BranchDao branchDao;
	private ExtraDao extraDao;
	private SanctionDao sDao;
	
	private CarTypeService carTypeService;
	private SanctionService sanctionService;
	
	public BookingService(BookingDao bookingDao, CarTypeDao carTypeDao, CustomerDao customerDao,
			CustomerCategoryDao cCategoryDao, CarDao carDao, CarSpecificationDao carSpecDao, BranchDao branchDao,
			ExtraDao extraDao,SanctionDao sDao, CarTypeService carTypeService, SanctionService sanctionService) {
		super();
		this.bookingDao = bookingDao;
		this.carTypeDao = carTypeDao;
		this.customerDao = customerDao;
		this.cCategoryDao = cCategoryDao;
		this.carDao = carDao;
		this.carSpecDao = carSpecDao;
		this.branchDao = branchDao;
		this.extraDao = extraDao;
		this.sDao = sDao;
		this.carTypeService = carTypeService;
		this.sanctionService = sanctionService;
	}

	public BookingService(CarTypeService carTypeService) {
		super();
		this.carTypeService = carTypeService;
	}

	public BookingService(){

	}

	public int booking(String customerRut, String origin, String startDay, String startHour, String destiny, String endDay,
			String endHour, int carTypeId, List<BookingExtra> extras) throws ParseException, CreateException {
		
		carTypeService = new CarTypeService(carTypeDao, extraDao);
		sanctionService = new SanctionService(sDao);
		
		if(sanctionService.searchUserWithSanction(customerRut, startDay))
			throw new CreateException();
		
		Customer customer = customerDao.getCustomer(customerRut);
		CustomerCategory cCategory = cCategoryDao.getCustomerCategoryById(customer.getIdCustomerCategory());
		List<Branch> branches = new ArrayList<>();
		branches.add(branchDao.get(origin));
		branches.add(branchDao.get(destiny));		
		
		List<Car> cars = carDao.getAllByBranchId(origin);
		List<CarSpecification> carSpecs = carSpecDao.getAllCarsByType(carTypeId);
		Car carBooked = null;
		
		for(Car car :  cars){
			CarSpecification carSpec = carSpecDao.get(car.getIdCarSpecification());
			if(carSpec != null){
				for(CarSpecification spec : carSpecs){
					if(carSpec.getId() == spec.getId()){		
						carBooked = car;
					}
				}
			}
		}
		
		int price = carTypeService.getPrice(startDay, endDay, carTypeId, extras);
		Booking booking = new Booking(startDay, endDay, price, customerRut, carBooked.getLicensePlate(), branches, extras);		
		bookingDao.create(booking);
		
		return price;
	}

	public List<Booking> getBookingsByRangeDateAndCustomer(String rutCustomer, String startRangeDate, String endRangeDate) throws EmptyListException, ParseException {
		Date dateStartRange, dateStartBooking, dateEndRange;
		dateStartRange = convertDate(startRangeDate);
		List<Booking> bookings = new ArrayList<Booking>();
		List<Booking> result = new ArrayList<Booking>();
		bookings = bookingDao.getAllBookingByCostumer(rutCustomer);
		if(bookings.isEmpty()){
			throw new EmptyListException();
		}
		if(endRangeDate.equals("")){
			for(Booking booking: bookings){
				dateStartBooking = convertDate(booking.getStartDate());
				if(startRangeDate.equals(booking.getStartDate()) || dateStartRange.before(dateStartBooking)){
					result.add(booking);
				}
			}
			return result;
		}else{
			dateEndRange = convertDate(endRangeDate);
			for(Booking booking: bookings){
				dateStartBooking = convertDate(booking.getStartDate());
				if(startRangeDate.equals(booking.getStartDate()) || dateStartRange.before(dateStartBooking)){
					if(endRangeDate.equals(booking.getStartDate()) || dateStartBooking.before(dateEndRange)){
						result.add(booking);
					}
					
				}
			}
			return result;
		}
						
	}
	
	private Date convertDate(String stringDate) throws ParseException{
		DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		Date convertedDate = null;
		convertedDate = date.parse(stringDate);
		return convertedDate;
		
	}

}
