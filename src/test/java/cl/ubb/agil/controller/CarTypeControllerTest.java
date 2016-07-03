package cl.ubb.agil.controller;




import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doThrow;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;


import cl.ubb.agil.model.CarType;
import cl.ubb.agil.service.CarTypeService;
import cl.ubb.agil.service.exception.EmptyListException;


import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;



@RunWith(MockitoJUnitRunner.class)
public class CarTypeControllerTest {
	
	private List<CarType> myCarTypes = new ArrayList<CarType>();
	
	private CarType carType1,carType2;
	
	@Mock
	private CarTypeService carTypeServiceMock;
	
	@InjectMocks
	private CarTypeController carTypeController;
	
	@Before
	public void setup(){
		carType1 = new CarType(1,"sports","automatic","fuel","yes",2,2,55000);
		carType2 = new CarType(2,"familiar","manual","fuel","yes",4,5,35000);
		myCarTypes.add(carType1);
		myCarTypes.add(carType2);
		RestAssuredMockMvc.standaloneSetup(carTypeController);
	}
	
	@Test
	public void shouldReturnAListOfCarTypesWhenGetAllCarTypesIsCalled() throws EmptyListException{
		when(carTypeServiceMock.getAllCarTypes()).thenReturn(myCarTypes);
		
		given().
			contentType(ContentType.JSON).
		when().
			get("/cartype/list").
		then().
			body("name[0]", equalTo("sports")).
			body("transmisionType[1]", equalTo("manual")).
			statusCode(SC_OK);
		
	}
	
	@Test
	public void shouldBeEmptyListWhenCallGetAllCarTypesAndNoCarTypeExist() throws EmptyListException{
		doThrow(new EmptyListException()).when(carTypeServiceMock).getAllCarTypes();
		
		given().
		when().
			get("/cartype/list").
		then().
			statusCode(SC_NOT_FOUND);
	}
	
	

}
