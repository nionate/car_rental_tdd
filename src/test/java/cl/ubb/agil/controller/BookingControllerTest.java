package cl.ubb.agil.controller;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.any;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

import cl.ubb.agil.model.Booking;
import cl.ubb.agil.model.BookingExtra;
import cl.ubb.agil.model.Branch;
import cl.ubb.agil.model.CarType;
import cl.ubb.agil.service.BookingService;
import cl.ubb.agil.service.CarTypeService;
import cl.ubb.agil.service.exception.CreateException;
import cl.ubb.agil.service.exception.EmptyListException;

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
	
	@Test
	public void shouldReturnAValueToPayWhenAClientBookACarWithOutExtras() throws ParseException, CreateException{
		
		List<BookingExtra> extras = new ArrayList<>();			
		
		when(bookingService.booking(
				Mockito.eq("18770816-8"), 
				Mockito.eq("1"), 
				Mockito.eq("11/06/2016"),
				Mockito.eq("15:00"), 
				Mockito.eq("1"),
				Mockito.eq("15/06/2016"), 
				Mockito.eq("15:00"), 
				Mockito.eq(1), 
				anyListOf(BookingExtra.class)))
		.thenReturn(40000);
				
		given().
			contentType(ContentType.JSON).
			body(extras).
		when().
			post("/booking/bookingACar/18770816-8/1/15:00/1/15:00/1/?startDate=11/06/2016&endDate=15/06/2016").
		then().
			assertThat().
			body(equalTo("40000")).
			statusCode(SC_OK);
	}
	
	@Test
	public void shouldReturnAListWithTwoBookings() throws EmptyListException, ParseException{
		
		List<Booking> bookings = new ArrayList<>();
		bookings.add(new Booking("10/06/2016", "10/07/2016", 40000, "18431210-7", "RRHH38", null, null));
		bookings.add(new Booking("10/05/2016", "09/06/2016", 50000, "18431210-7", "BBJJ12", null, null));
		when(bookingService.getBookingsByRangeDateAndCustomer("18431210-7", "09/05/2016", "11/07/2016")).thenReturn(bookings);
		
		given().
			contentType(ContentType.JSON).
		when().
			get("/booking/bookingsList/18431210-7/?startDate=09/05/2016&endDate=11/07/2016").
		then().
			assertThat().
			body("licensePlate[0]", equalTo(bookings.get(0).getLicensePlate())).
			body("licensePlate[1]", equalTo(bookings.get(1).getLicensePlate())).
			statusCode(SC_OK);
	}
	
	@Test
	public void shouldReturnAnEmptyListExceptionWhenTheCustomerNotHaveBookings() throws EmptyListException, ParseException{
		
		doThrow(new EmptyListException()).when(bookingService).getBookingsByRangeDateAndCustomer("18431210-7", "11/07/2016", "31/12/2016");
		
		given().
		when().
			get("/booking/bookingsList/18431210-7/?startDate=11/07/2016&endDate=31/12/2016").
		then().
			assertThat().
				statusCode(SC_NOT_FOUND);
	}
}
