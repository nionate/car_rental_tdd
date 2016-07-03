package cl.ubb.agil.service;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import cl.ubb.agil.dao.TimeConstraintDao;
import cl.ubb.agil.model.CustomerCategory;
import cl.ubb.agil.model.TimeConstraint;

@RunWith(MockitoJUnitRunner.class)
public class TimeConstraintServiceTest {
	
	@Mock
	private TimeConstraintDao timeCDao;
	
	@InjectMocks
	private TimeConstraintService timeCService;
	
	private TimeConstraint timeC1, timeC2; 
	private CustomerCategory CCPerson, CCCompany;
	
	@Before
	public void setup() {
		timeC1= new TimeConstraint(2,10,2,1);
		timeC2= new TimeConstraint(5,30,4,2);
		CCPerson = new CustomerCategory(1,"Person");
		CCCompany = new CustomerCategory(2,"Company");
	}
		
	@Test
	public void canBookingCustomerCategoryPersonAndCarTypeTwoBetweenTwoAndTenDaysRetornTrueTest(){
		int carType = 2, minDays = 2, maxDays = 10; 
		when(timeCDao.getAllByCustomerCategoryAndCarType(CCPerson.getIdentifier(),carType)).thenReturn(timeC1);
		
		assertEquals(true, timeCService.canBooking(minDays, maxDays, carType, CCPerson.getIdentifier()));
	
	}
	@Test
	public void canBookingCustomerCategoryCompanyAndCarTypeFourBetweenFiveAndThirtyDaysRetornTrueTest(){
		int carType = 4, minDays = 5, maxDays = 30;
		when(timeCDao.getAllByCustomerCategoryAndCarType(CCCompany.getIdentifier(),carType)).thenReturn(timeC2);
		
		assertEquals(true, timeCService.canBooking(minDays, maxDays, carType, CCCompany.getIdentifier()));
	
	}
	@Test
	public void canBookingCustomerCategoryPersonAndCarTypeTwoForOneDayRetornFalseTest(){
		int carType = 2, minDays = 1, maxDays = 1; 
		when(timeCDao.getAllByCustomerCategoryAndCarType(CCPerson.getIdentifier(),carType)).thenReturn(timeC1);
	
		assertEquals(false, timeCService.canBooking(minDays, maxDays, carType,CCPerson.getIdentifier()));
	
	}
	@Test
	public void canBookingCustomerCategoryCompanyAndCarTypeFourForFourDaysRetornFalseTest(){
		int carType = 4, minDays = 4, maxDays = 4;
		when(timeCDao.getAllByCustomerCategoryAndCarType(CCCompany.getIdentifier(),carType)).thenReturn(timeC2);
			
		assertEquals(false, timeCService.canBooking(minDays, maxDays, carType, CCCompany.getIdentifier()));
	
	}

	
	
	
	

}
