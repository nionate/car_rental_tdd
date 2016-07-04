package cl.ubb.agil.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.apache.http.HttpStatus.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.equalTo;
import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Mockito.doThrow;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

import cl.ubb.agil.model.CarSpecification;
import cl.ubb.agil.service.CarSpecificationService;
import cl.ubb.agil.service.exception.EmptyListException;

@RunWith(MockitoJUnitRunner.class)
public class CarSpecificationControllerTest {
	
	private List<CarSpecification> listCarSpecification = new ArrayList<CarSpecification>();
	
	private CarSpecification cS1,cS2,cS3;

	@Mock
	private CarSpecificationService carSpecificationService;
	
	@InjectMocks
	private CarSpecificationController carSpecificationController;
	
	@Before
	public void setup(){
		cS1 = new CarSpecification(1,"Toyota","Yaris","2004");
		cS2 = new CarSpecification(2,"Nissan","V-16","1998");
		cS3 = new CarSpecification(3,"Hyundai","Tucson","2010");
		listCarSpecification.add(cS1);
		listCarSpecification.add(cS2);
		listCarSpecification.add(cS3);
		RestAssuredMockMvc.standaloneSetup(carSpecificationController);
	}
	
	@Test
	public void getListCarSpecificationByCarTypeIdTest() throws EmptyListException{
		when(carSpecificationService.getAllCarsByType(4)).thenReturn(listCarSpecification);
		
		given().
		when().
			get("/carspecification/listbyid/{idCartype}",4).
		then().
			body("brand[0]", equalTo("Toyota")).
			body("brand[1]", equalTo("Nissan")).
			body("year[2]", equalTo("2010")).
			statusCode(SC_OK);
			
	}
	
	@Test
	public void emptyListCarSpecificationByCarTypeIdTest() throws EmptyListException{
		doThrow(new EmptyListException()).when(carSpecificationService).getAllCarsByType(4);
		
		given().
		when().
			get("/carspecification/listbyid/{idCartype}",4).
		then().
			statusCode(SC_NOT_FOUND);
	}
	

}
