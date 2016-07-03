package cl.ubb.agil.controller;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

import cl.ubb.agil.model.Branch;
import cl.ubb.agil.service.BranchService;
import cl.ubb.agil.service.exception.EmptyListException;

@RunWith(MockitoJUnitRunner.class)
public class BranchControllerTest {
	
	private List<Branch> branches = new ArrayList<>();
	private Branch branch1, branch2;
	
	@Mock
	private BranchService branchService;
	@InjectMocks
	private BranchController branchController;
	
	@Before
	public void setUp(){
		branch1 = new Branch("001", "Chillán", "Terminal de buses");
		branch2 = new Branch("002", "Chillán", "Estacion de trenes");
		branches.add(branch1);
		branches.add(branch2);
		RestAssuredMockMvc.standaloneSetup(branchController);
	}
	
	@Test
	public void shouldBeEmptyWhenGetAllBranchesIsCalledAndNoBranchExist() throws EmptyListException{
		
		doThrow(new EmptyListException()).when(branchService).getAll();
		
		given().
		when().
			get("/branch/list").
		then().
			assertThat().
				statusCode(SC_NOT_FOUND);
	}
	
	@Test
	public void shouldReturnAListOfBranchWhenGetAllBranchesIsCalled() throws EmptyListException{
		
		when(branchService.getAll()).thenReturn(branches);
		
		given().
		when().
			get("/branch/list").
		then().
			assertThat().
				body("identifier[0]", equalTo(branches.get(0).getIdentifier())).
				body("identifier[1]", equalTo(branches.get(1).getIdentifier())).
				statusCode(SC_OK);
	}
}
