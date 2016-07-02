package cl.ubb.agil.dao;

import java.util.List;

//import cl.ubb.agil.model.CarSpecification;
import cl.ubb.agil.model.CarType;
import cl.ubb.agil.model.TimeConstraint;

public interface CarTypeDao {
	
	
	public List <CarType> getAllCarTypes();
	public CarType getCarType(int id);
	

}
