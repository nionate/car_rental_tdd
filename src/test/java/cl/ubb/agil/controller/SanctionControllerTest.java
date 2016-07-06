package cl.ubb.agil.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

import cl.ubb.agil.service.SanctionService;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;

@RunWith(MockitoJUnitRunner.class)
public class SanctionControllerTest {

@Mock
private SanctionService sanctionService;
@InjectMocks
private SanctionController sanctionController;
@Before
public void setup(){
	RestAssuredMockMvc.standaloneSetup(sanctionController);
}

@Test
public void ShoulReturnFalseWhenCallASearchUserWithSanction(){
	when(sanctionService.searchUserWithSanction("18770816-8","03/10/15")).thenReturn(false);
	given().
	when().
		get("/sanction/haveActiveSanctionByCustomer/18770816-8/?date=03/10/15").
	then().
		assertThat().
		body("result",equalTo(false)).
		statusCode(SC_OK);
}

}
