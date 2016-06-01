package cl.ubb.agil.dao;

import java.util.List;

import cl.ubb.agil.model.CarSpecification;

public interface CarSpecificationDao {

	public List<CarSpecification> getAllCarsByType(int identifier);

}
