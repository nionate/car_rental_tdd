package cl.ubb.agil.dao;

import java.util.List;

import cl.ubb.agil.model.Promotion;

public interface PromotionDao {
	
	public List<Promotion> getAllByCarType(int idCarType);

}
