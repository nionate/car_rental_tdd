package cl.ubb.agil.service;

import java.util.ArrayList;
import java.util.List;

import cl.ubb.agil.dao.CarTypeDao;
import cl.ubb.agil.model.CarType;

public class CarTypeService {
	
	private CarTypeDao carTypeDao;
	
	public List <CarType> getAllCarTypes(){
		List <CarType> carsTypes = new ArrayList <CarType>();
		carsTypes = carTypeDao.getAllCarTypes();
		return carsTypes;
	}

}
