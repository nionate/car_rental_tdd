package cl.ubb.agil.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TimeConstraintTest {
	
	private TimeConstraint sut;
	
	@Before
	public void setup(){
		sut = new TimeConstraint();
	}

	@Test
	public void minNumberOfDaysIsFour(){
		

		sut.setMinNumberOfDays(4);
		
		int result = sut.getMinNumberOfDays();
		
		assertEquals(4, result);
		
	}
	
	@Test
	public void minNumberOfDaysIsSix(){

		sut.setMinNumberOfDays(6);
		
		int result = sut.getMinNumberOfDays();
		
		assertEquals(6, result);
	}
	
	@Test
	public void minNumberOfDaysIsTen(){

		sut.setMinNumberOfDays(10);
		
		int result = sut.getMinNumberOfDays();
		
		assertEquals(10, result);
		
	}
	
	@Test
	public void maxNumberOfDaysIsSix(){
		
		sut.setMaxNumberOfDays(6);
		
		int result = sut.getMaxNumberOfDays();
		
		assertEquals(6, result);
	}
	
	@Test
	public void maxNumberOfDaysIsEight(){
		
		sut.setMaxNumberOfDays(8);
		
		int result = sut.getMaxNumberOfDays();
		
		assertEquals(8, result);
	}
	
	@Test
	public void maxNumberOfDaysIsTwelve(){
		
		sut.setMaxNumberOfDays(12);
		
		int result = sut.getMaxNumberOfDays();
		
		assertEquals(12, result);
	}
	
	@Test
	public void minNumberOfDaysLessThanZeroThrowException(){
		
	}
	
	@Test
	public void maxNumberOfDaysLessThanZeroThrowException(){
		
	}
	
	@Test
	public void minNumberOfDaysGreaterThanMaxNumberOfDaysThrowException(){
		
	}
	
	@Test
	public void maxNumberOfDaysLessThanMinNumberOfDaysThrowException(){
		
	}
}
