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

import cl.ubb.agil.model.CustomerCategory;
import cl.ubb.agil.service.CustomerCategoryService;
import cl.ubb.agil.service.exception.EmptyListException;

@RunWith(MockitoJUnitRunner.class)
public class CustomerCategoryControllerTest {

	private List<CustomerCategory> categories = new ArrayList<>();
	private CustomerCategory customerCategory1, customerCategory2;
	
	@Mock
	private CustomerCategoryService customerCategoryService;
	@InjectMocks
	private CustomerCategoryController customerCategoryController;
	
	@Before
	public void setUp(){
		customerCategory1 = new CustomerCategory (1,"Person");
		customerCategory2 = new CustomerCategory (2,"Company");
		categories.add(customerCategory1);
		categories.add(customerCategory2);
		RestAssuredMockMvc.standaloneSetup(customerCategoryController);
	}
	
	@Test
	public void shouldBeEmptyWhenGetAllCustomerCategoriesIsCalledAndNoCustomerCategoriesExist() throws EmptyListException{
		
		doThrow(new EmptyListException()).when(customerCategoryService).getAllCategories();
		
		given().
		when().
			get("/customerCategory/list").
		then().
			assertThat().
				statusCode(SC_NOT_FOUND);
	}
	
	@Test
	public void shouldReturnAListOfCustomerCategoriesWhenGetAllCustomerCategoriesIsCalled() throws EmptyListException{
		
		when(customerCategoryService.getAllCategories()).thenReturn(categories);
		
		given().
		when().
			get("/customerCategory/list").
		then().
			assertThat().
				body("identifier[0]", equalTo(categories.get(0).getIdentifier())).
				body("identifier[1]", equalTo(categories.get(1).getIdentifier())).
				statusCode(SC_OK);
	}
	
	
}
