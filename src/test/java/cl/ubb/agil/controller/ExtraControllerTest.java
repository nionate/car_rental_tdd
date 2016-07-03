package cl.ubb.agil.controller;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

import cl.ubb.agil.model.Extra;
import cl.ubb.agil.service.ExtraService;
import cl.ubb.agil.service.exception.EmptyListException;

@RunWith(MockitoJUnitRunner.class)
public class ExtraControllerTest {
	
	private ArrayList<Extra> extras = new ArrayList<>();
	private Extra extra1, extra2, extra3;
	
	@Mock
	private ExtraService extraService;
	@InjectMocks
	private ExtraController extraController;
	
	@Before
	public void setUp(){
		extra1 = new Extra(1111,"Baby seat", "blue chair", 8000);
		extra2 = new Extra(1112,"GPS", "This is a GPS", 8000);
		extra3 = new Extra(1113,"Cable","Cable to connect auxiliary",2000);
		extras.add(extra1);
		extras.add(extra2);
		extras.add(extra3);
		RestAssuredMockMvc.standaloneSetup(extraController);
	}
	
	@Test
	public void shouldBeEmptyWhenGetAllIsCalledAndNoExistExtraTest() throws EmptyListException{
		
		doThrow(new EmptyListException()).when(extraService).getAll();
		
		given().
		when().
			get("/extra/list").
		then().
			assertThat().
				statusCode(SC_NOT_FOUND);
	}
	@Test
	public void shouldReturnAListOfExtraWhenGetAllIsCalled() throws EmptyListException{
		
		when(extraService.getAll()).thenReturn(extras);
		
		given().
		when().
			get("/extra/list").
		then().
			assertThat().
				body("identifier[0]", equalTo(extras.get(0).getIdentifier())).
				body("identifier[1]", equalTo(extras.get(1).getIdentifier())).
				body("identifier[2]", equalTo(extras.get(2).getIdentifier())).
				statusCode(SC_OK);
	}
	
}
