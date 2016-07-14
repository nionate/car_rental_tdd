package cl.ubb.agil.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

import cl.ubb.agil.model.BookingExtra;
import cl.ubb.agil.model.Branch;
import cl.ubb.agil.model.CarType;
import cl.ubb.agil.service.BookingService;
import cl.ubb.agil.service.CarTypeService;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookingControllerTest {
	
	@Mock
	private BookingService bookingService;
	
	@InjectMocks
	private BookingController bookingController;
	
	@Before
	public void setup(){
		RestAssuredMockMvc.standaloneSetup(bookingController);
	}
	
	private String[] fillExtraStrings(List<BookingExtra> extras){
		String[] results = {"",""};
		
		if(extras.size() == 1){
			results[0] += extras.get(extras.size()-1).getExtraId();
			results[1] += extras.get(extras.size()-1).getNumber();
		}else{
		
			for(int i = 0 ; i < extras.size(); i++){
				results[0] += extras.get(i).getExtraId() + ",";
				results[1] += extras.get(i).getNumber() + ",";
			}
			if(extras.size()-1>0){
				results[0] += extras.get(extras.size()-1).getExtraId();
				results[1] += extras.get(extras.size()-1).getNumber();
			}
		}
		
		return results;
	}
	
	@Test
	public void shouldReturnAValueToPayWhenAClientBookACarWithOutExtras() throws ParseException{
		
		/*String rutCliente = "18770816-8";
		String startDay = "11/06/2016";
		String endDay = "15/06/2016";
		String bookingHour = "15:00";
		Branch santiago = new Branch("1", "Santiago", "");
		CarType type = new CarType(1, "", "automatic", "diesel", "", 4, 5, 10000);*/
		List<BookingExtra> extras = new ArrayList<>();
		extras.add(new BookingExtra(1, 1));
		/*String[] extraStrings = fillExtraStrings(extras);
		
		String extrasIds = extraStrings[0];
		String extrasQuantity = extraStrings[1];*/
		
		
		when(bookingService.booking(
				"18770816-8", 
				"1", 
				"11/06/2016",
				"15:00", 
				"1",
				"15/06/2016", 
				"15:00", 
				1, 
				extras))
		.thenReturn(40000);

		given().
			contentType(ContentType.JSON).
			body(extras).
		when().
			post("/booking/bookingACar/18770816-8/1/15:00/1/15:00/1/?startDate=11/06/2016&endDate=15/06/2016").
		then().
			assertThat().
			body("result",equalTo(40000)).
			statusCode(SC_OK);
	}
}
