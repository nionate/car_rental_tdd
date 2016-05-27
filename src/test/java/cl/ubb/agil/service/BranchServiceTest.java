package cl.ubb.agil.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import cl.ubb.agil.dao.BranchDao;
import cl.ubb.agil.model.Branch;
import cl.ubb.agil.service.exception.ReadErrorException;

@RunWith(MockitoJUnitRunner.class)
public class BranchServiceTest {

	@Mock
	private BranchDao bdao;
	
	@InjectMocks
	private BranchService bservice;
	
	@Test
	public void getAllBranchesReturnListWithOneObjectTest() throws Exception{
		
		List<Branch> branchs = new ArrayList<Branch>();
		branchs.add(new Branch());
			
		when(bdao.getAllBranches()).thenReturn(branchs);
		
		List<Branch> result = bservice.getAllBranches();
		
		assertEquals(1, result.size());
		
	}
	
	@Test
	public void getAllBranchesReturnListWithMoreThanOneObjectTest() throws Exception{
		
		List<Branch> branchs = new ArrayList<Branch>();
		branchs.add(new Branch());
		branchs.add(new Branch());
			
		when(bdao.getAllBranches()).thenReturn(branchs);
		
		List<Branch> result = bservice.getAllBranches();
		
		assertEquals(2, result.size());
		
	}
	
	@Test(expected = ReadErrorException.class)
	public void getAllBranchesReturnAnEmptyListThrowExceptionTest() throws Exception{
		
		List<Branch> branchs = new ArrayList<Branch>();
		
		when(bdao.getAllBranches()).thenReturn(branchs);
		
		List<Branch> result = bservice.getAllBranches();
		
		assertEquals(true, result.isEmpty());
		
	}
	
	@Test
	public void searchBranchTest(){
		
		Branch br = new Branch();
		
		when(bdao.searchBranch(1)).thenReturn(br);
		
		Branch result = bservice.searchBranch(1);
		
		assertEquals(1, result.getIdentifier());
	}
	
	
	
}
