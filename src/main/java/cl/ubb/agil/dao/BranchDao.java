package cl.ubb.agil.dao;

import java.util.List;

import cl.ubb.agil.model.Branch;

public interface BranchDao {

	public List<Branch> getAllBranches();

	public Branch searchBranch(int i);

}
