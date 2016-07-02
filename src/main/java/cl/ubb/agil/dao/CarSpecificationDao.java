package cl.ubb.agil.dao;

import java.util.List;

import cl.ubb.agil.model.CarSpecification;

public interface CarSpecificationDao {

	public CarSpecification get(int idCarSpecification);
	public List<CarSpecification> getAllCarsByType(int identifier);

}
