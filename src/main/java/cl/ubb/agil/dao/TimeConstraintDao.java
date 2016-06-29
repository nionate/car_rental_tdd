package cl.ubb.agil.dao;

import java.util.ArrayList;

import cl.ubb.agil.model.TimeConstraint;

public interface TimeConstraintDao {

	public ArrayList <TimeConstraint> getAll();
	
	public TimeConstraint getAllByCustomerCategoryAndCarType(int idCustomerCategory,int idCarType);
	
	
}
