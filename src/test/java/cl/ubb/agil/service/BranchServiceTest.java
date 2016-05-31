package cl.ubb.agil.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import cl.ubb.agil.dao.BranchDao;
import cl.ubb.agil.model.Branch;
import cl.ubb.agil.service.exception.EmptyListException;

@RunWith(MockitoJUnitRunner.class)
public class BranchServiceTest {

	@Mock
	private BranchDao branchDao;
	private Branch branch;
	
	@InjectMocks
	private BranchService branchService;
	
	
	@Test
	public void sizeOfGetAllBranchesIsOneTest() throws EmptyListException{
		/*Arrange*/
		Branch branch = new Branch (1111, "Santiago", "Terminal TurBus");
		ArrayList<Branch> branches = new ArrayList<Branch>();
		branches.add(branch);
		when(branchDao.getAll()).thenReturn(branches);
		
		/*Act*/
		ArrayList<Branch> listBranches = branchService.getAll();
		
		/*Assert*/
		assertThat(listBranches, is(equalTo(branches)));
	}
	
	@Test
	public void sizeOfGetAllBranchesIsTwoTest() throws EmptyListException{
		/*Arrange*/
		Branch branch = new Branch (1111, "Santiago", "Terminal TurBus");
		Branch branch1 = new Branch (1112, "Santiago", "Aeropuerto Comodoro");
		ArrayList<Branch> branches = new ArrayList<Branch>();
		branches.add(branch);
		branches.add(branch1);
		when(branchDao.getAll()).thenReturn(branches);
		
		/*Act*/
		ArrayList<Branch> listBranches = branchService.getAll();
		
		/*Assert*/
		assertThat(listBranches, is(equalTo(branches)));;
	}
	
	@Test (expected=EmptyListException.class)
	public void GetAllBranchesReturnEmptyTest()throws EmptyListException{
		/*Arrange*/
		ArrayList<Branch> branches = new ArrayList<Branch>();
		when(branchDao.getAll()).thenReturn(branches);
		
		/*Act*/
		branchService.getAll();
	}
	
	
	
}
