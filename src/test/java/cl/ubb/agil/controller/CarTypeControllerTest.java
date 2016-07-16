package cl.ubb.agil.controller;




import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.when;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doThrow;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

import cl.ubb.agil.model.BookingExtra;
import cl.ubb.agil.model.CarType;
import cl.ubb.agil.service.CarTypeService;
import cl.ubb.agil.service.exception.EmptyListException;


import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;



@RunWith(MockitoJUnitRunner.class)
public class CarTypeControllerTest {
	
	private List<CarType> myCarTypes = new ArrayList<CarType>();
	private List<BookingExtra> bkExtras = new ArrayList<BookingExtra>();
	
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
		BookingExtra bkExtra1 = new BookingExtra(1,1);
		BookingExtra bkExtra2 = new BookingExtra(1,2);
		bkExtras.add(bkExtra1);
		bkExtras.add(bkExtra2);
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
	
	@Test
	public void shouldReturnThePriceOfTypeCarInCertainDateWithExtras() throws ParseException{
		when(carTypeServiceMock.getPrice(
				Mockito.eq("09/07/2016"),
				Mockito.eq("11/07/2016"),
				Mockito.eq(2),
				anyListOf(BookingExtra.class))).thenReturn(60000);
		
		given().
			contentType(ContentType.JSON).
			body(bkExtras).
		when().
			post("/cartype/value/2/?startDate=09/07/2016&endDate=11/07/2016").
		then().
			assertThat().
			body(equalTo("60000")).
			statusCode(SC_OK);
	}
	
	@Test
	public void shouldReturnParseExceptionBecauseDateIsMispelled() throws ParseException{
		
		doThrow(new ParseException(null, 0)).when(carTypeServiceMock).getPrice(Mockito.eq("09/07/2016"),
				Mockito.eq("11/07/2016"),
				Mockito.eq(2),
				anyListOf(BookingExtra.class));
		
			
		given().
			contentType(ContentType.JSON).
			body(bkExtras).
		when().
			post("/cartype/value/2/?startDate=09-07-2016&endDate=11/07/2016").
		then();
			
	}
	
	

}
