package cl.ubb.agil.dao;

import java.util.ArrayList;

import cl.ubb.agil.model.Extra;

public interface ExtraDao {
	
	public Extra get(int id);
	public ArrayList<Extra> getAll();
	
}
