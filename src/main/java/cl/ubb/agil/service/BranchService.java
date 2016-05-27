package cl.ubb.agil.service;

import java.util.List;

import cl.ubb.agil.dao.BranchDao;
import cl.ubb.agil.model.Branch;
import cl.ubb.agil.service.exception.ReadErrorException;

public class BranchService {
	
	private BranchDao bdao;

	public List<Branch> getAllBranches() throws ReadErrorException{
		List<Branch> branches = bdao.getAllBranches();
		
		if(branches.isEmpty())
			throw new ReadErrorException();
		
		return bdao.getAllBranches();
	}

	public Branch searchBranch(int i) {
		return bdao.searchBranch(i);
	}

}
