package cl.ubb.agil.service;

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
import cl.ubb.agil.model.Booking;
import cl.ubb.agil.model.BookingExtra;
import cl.ubb.agil.model.Branch;
import cl.ubb.agil.model.Car;
import cl.ubb.agil.model.CarSpecification;
import cl.ubb.agil.model.Customer;
import cl.ubb.agil.model.CustomerCategory;
import cl.ubb.agil.model.Extra;

public class BookingService {
	
	private BookingDao bookingDao;
	private CarTypeDao carTypeDao;
	private CustomerDao customerDao;
	private CustomerCategoryDao cCategoryDao;
	private CarDao carDao;
	private CarSpecificationDao carSpecDao;
	private BranchDao branchDao;
	private ExtraDao extraDao;
	
	private CarTypeService carTypeService;
	
	public BookingService(){

	}

	public int booking(String customerRut, String origin, String startDay, String startHour, String destiny, String endDay,
			String endHour, int carTypeId, List<BookingExtra> extras) throws ParseException {
		
		carTypeService = new CarTypeService(carTypeDao, extraDao);
		
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

}