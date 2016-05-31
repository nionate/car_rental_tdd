package cl.ubb.agil.service;

import java.util.ArrayList;

import cl.ubb.agil.dao.ExtraDao;
import cl.ubb.agil.model.Extra;
import cl.ubb.agil.service.exception.EmptyListException;

public class ExtraService {
	private ExtraDao extraDao;
	
	public ArrayList<Extra> getAll() throws EmptyListException {
		ArrayList<Extra> extras;
		extras = extraDao.getAll();
		if (!extras.isEmpty()) {
			return extras;
		}
		throw new EmptyListException();
	}

}
