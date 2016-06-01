package cl.ubb.agil.service;

import java.util.List;

import cl.ubb.agil.dao.CarSpecificationDao;
import cl.ubb.agil.model.CarSpecification;
import cl.ubb.agil.model.CarType;
import cl.ubb.agil.service.exception.EmptyListException;

public class CarSpecificationService {
	
	private CarSpecificationDao csDao;

	public List<CarSpecification> getAllCarsByType(CarType carType) throws EmptyListException {
		
		List<CarSpecification> cars = csDao.getAllCarsByType(carType.getIdentifier());
		
		if(cars.isEmpty())
			throw new EmptyListException();
		
		return cars;
	}

}
