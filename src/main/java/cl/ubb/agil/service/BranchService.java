package cl.ubb.agil.service;

import java.util.List;

import cl.ubb.agil.dao.BranchDao;
import cl.ubb.agil.model.Branch;
import cl.ubb.agil.service.exception.EmptyListException;
public class BranchService {
	
	private BranchDao bdao;

	public List<Branch> getAll() throws EmptyListException{
		List<Branch> branches = bdao.getAll();
		
		if(branches.isEmpty())
			throw new EmptyListException();
		
		return bdao.getAll();
	}

}
