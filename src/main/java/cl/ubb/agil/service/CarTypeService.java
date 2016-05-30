package cl.ubb.agil.service;

import java.util.ArrayList;
import java.util.List;

import cl.ubb.agil.dao.CarTypeDao;
import cl.ubb.agil.model.CarType;
import cl.ubb.agil.service.exception.EmptyListException;

public class CarTypeService {
	
	private CarTypeDao carTypeDao;
	
	public List <CarType> getAllCarTypes() throws EmptyListException{
		List <CarType> carsTypes = new ArrayList <CarType>();
		carsTypes = carTypeDao.getAllCarTypes();
		if(carsTypes.isEmpty()){
			throw new EmptyListException();
		}else
			return carsTypes;
	}
}
