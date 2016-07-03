package cl.ubb.agil.controller;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.mockito.Mockito.doThrow;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

import cl.ubb.agil.service.ExtraService;
import cl.ubb.agil.service.exception.EmptyListException;

@RunWith(MockitoJUnitRunner.class)
public class ExtraControllerTest {
	
	@Mock
	private ExtraService extraService;
	@InjectMocks
	private ExtraController extraController;
	
	@Before
	public void setUp(){
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
	
}
