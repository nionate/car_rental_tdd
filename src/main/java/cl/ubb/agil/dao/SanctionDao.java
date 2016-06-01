package cl.ubb.agil.dao;

import java.util.List;

import cl.ubb.agil.model.Sanction;

public interface SanctionDao {

	public Sanction get(String identifier);

	public List<Sanction> getAll();

	public List<Sanction> getAllByCostumer(String rut);


}
