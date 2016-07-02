package cl.ubb.agil.service;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cl.ubb.agil.dao.CarDao;
import cl.ubb.agil.dao.CarSpecificationDao;
import cl.ubb.agil.dao.CustomerDao;
import cl.ubb.agil.dao.TimeConstraintDao;
import cl.ubb.agil.model.Car;
import cl.ubb.agil.model.CarSpecification;
import cl.ubb.agil.model.CarType;
import cl.ubb.agil.model.Customer;
import cl.ubb.agil.model.CustomerCategory;
import cl.ubb.agil.model.TimeConstraint;
import cl.ubb.agil.service.exception.EmptyListException;

public class CarService {
	
	private CustomerDao customerDao;
	private TimeConstraintDao constraintDao;
	private CarDao carDao;
	private CarSpecificationDao carSpecificationDao;

	public Car getACarIfPermitted(String customerRut, CarType carType, int idOriginBranch, String startDate, int idDestinyBranch,
			String endDate, HashMap<Integer, Integer> extras) throws EmptyListException {

		Car car = null;
		
		Customer customer = customerDao.getCustomer(customerRut);
		int category = customer.getIdCustomerCategory();
		List<TimeConstraint> constraints = constraintDao.getAll();
		TimeConstraint constraint = null;
		
		for(TimeConstraint cons : constraints){
			if(cons.getIdentifierCarType() == carType.getIdentifier() && cons.getIdentifierCustomerCategory() == category){
				constraint = cons;
				break;
			}
		}
		
		
		int diff_days = diffDays(stringToDate(endDate), stringToDate(startDate));
		
		boolean found1 = false, found2= false;
		if(diff_days >= constraint.getMinNumberOfDays() && diff_days <= constraint.getMaxNumberOfDays()){
			List<CarSpecification> specifications = carSpecificationDao.getAllCarsByType(carType.getIdentifier());
			List<Car> carsInBranch = carDao.getAllByBranchId(idOriginBranch);
			
			
			for(CarSpecification specification : specifications){
				List<Car> carsByCarSpec = carDao.getAllByCarSpecificationId(specification.getId());
				
				for(Car carBySpec : carsByCarSpec){
					for(Car carBranch : carsInBranch ){
						if(carBranch.getLicensePlate().equals(carBySpec.getLicensePlate())){
							car = carBranch;
							found1 = true;
							found2 = true;
							break;
						}
					}
					
					if(found1){
						break;
					}	
				}
				
				if(found2){
					break;
				}					
			}
		}
		
		return car;
	}
	
	public int diffDays(Date endDate, Date startDate) {
		long ms_diff = endDate.getTime() - startDate.getTime();
		long days = ms_diff / (1000 * 60 * 60 * 24);
		return (int) days;
	}
	
	public Date stringToDate(String dateString){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;

		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	
}
