package cl.ubb.agil.dao;

import java.util.List;

import cl.ubb.agil.model.Car;
import cl.ubb.agil.model.CarSpecification;

public interface CarDao {
	
	public List<Car> getAllByBranchId(int branchId);
	public List<Car> getAllByCarSpecificationId(int carSpecificationId);
}
