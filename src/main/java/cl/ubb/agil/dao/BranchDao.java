package cl.ubb.agil.dao;

import java.util.List;

import cl.ubb.agil.model.Branch;

public interface BranchDao {

	public Branch get(String id);
	public List<Branch> getAll();

}
