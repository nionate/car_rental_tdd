package cl.ubb.agil.service;

import java.util.ArrayList;

import cl.ubb.agil.dao.BranchDao;
import cl.ubb.agil.model.Branch;
import cl.ubb.agil.service.exception.EmptyListException;
public class BranchService {
	
	private BranchDao bdao;

	public ArrayList<Branch> getAll() throws EmptyListException{
		ArrayList<Branch> branches = bdao.getAll();
		
		if(branches.isEmpty())
			throw new EmptyListException();
		
		return bdao.getAll();
	}

}
