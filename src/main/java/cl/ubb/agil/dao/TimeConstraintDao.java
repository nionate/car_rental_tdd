package cl.ubb.agil.dao;

import java.util.List;

import cl.ubb.agil.model.TimeConstraint;

public interface TimeConstraintDao {

	public List<TimeConstraint> getAll();
	
	public TimeConstraint getAllByCustomerCategoryAndCarType(int idCustomerCategory,int idCarType);
	
	
}
