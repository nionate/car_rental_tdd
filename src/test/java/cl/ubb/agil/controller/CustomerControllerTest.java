package cl.ubb.agil.controller;




import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;



import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

import cl.ubb.agil.model.Customer;
import cl.ubb.agil.service.CustomerService;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {
	
	@Mock
	private CustomerService customerService;
	
	@InjectMocks
	private CustomerController customerController;
	
	@Before
	public void setup(){
		RestAssuredMockMvc.standaloneSetup(customerController);
	}
	
	@Test
	public void createCustomerTest(){
		given().
			contentType(ContentType.JSON).
			body(new Customer("18451564-4","Miguel","98765432","miguel@mail.cl",1)).
		when().
			post("/customer/create").
		then().
			statusCode(SC_CREATED).
			assertThat().
			body("rut",equalTo("18451564-4")).
			body("name",equalTo("Miguel")).
			body("cellPhone",equalTo("98765432"));
	}
	
	

}
