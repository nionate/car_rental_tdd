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
	public void constructorTimeConstraintSetMinAndMaxNumbersOfDays(){
		
		TimeConstraint sut2 = new TimeConstraint(4, 7);
		
		int result = sut2.getMinNumberOfDays();
		int result2 = sut2.getMaxNumberOfDays();
		
		assertEquals(4, result);
		assertEquals(7, result2);
	}

	@Test
	public void minNumberOfDaysIsFour() throws NumberOfDaysException{
		

		sut.setMinNumberOfDays(4);
		
		int result = sut.getMinNumberOfDays();
		
		assertEquals(4, result);
		
	}
	
	@Test
	public void minNumberOfDaysIsSix() throws NumberOfDaysException{

		sut.setMinNumberOfDays(6);
		
		int result = sut.getMinNumberOfDays();
		
		assertEquals(6, result);
	}
	
	@Test
	public void minNumberOfDaysIsTen() throws NumberOfDaysException{

		sut.setMinNumberOfDays(10);
		
		int result = sut.getMinNumberOfDays();
		
		assertEquals(10, result);
		
	}
	
	@Test
	public void maxNumberOfDaysIsSix() throws NumberOfDaysException{
		
		sut.setMaxNumberOfDays(6);
		
		int result = sut.getMaxNumberOfDays();
		
		assertEquals(6, result);
	}
	
	@Test
	public void maxNumberOfDaysIsEight() throws NumberOfDaysException{
		
		sut.setMaxNumberOfDays(8);
		
		int result = sut.getMaxNumberOfDays();
		
		assertEquals(8, result);
	}
	
	@Test
	public void maxNumberOfDaysIsTwelve() throws NumberOfDaysException{
		
		sut.setMaxNumberOfDays(12);
		
		int result = sut.getMaxNumberOfDays();
		
		assertEquals(12, result);
	}
	
	@Test(expected = NumberOfDaysException.class)
	public void minNumberOfDaysLessThanZeroThrowException() throws NumberOfDaysException{
		
		sut.setMinNumberOfDays(-5);
		
		int result = sut.getMinNumberOfDays();
	}
	
	@Test(expected = NumberOfDaysException.class)
	public void maxNumberOfDaysLessThanZeroThrowException() throws NumberOfDaysException{
		
		sut.setMaxNumberOfDays(-5);
		
		int result = sut.getMaxNumberOfDays();
	}
	
	@Test(expected = NumberOfDaysException.class)
	public void minNumberOfDaysGreaterThanMaxNumberOfDaysThrowException() throws NumberOfDaysException{
		
		sut.setMinNumberOfDays(4);
		sut.setMaxNumberOfDays(3);
	}
	
	@Test(expected = NumberOfDaysException.class)
	public void maxNumberOfDaysLessThanMinNumberOfDaysThrowException() throws NumberOfDaysException{
		
		sut.setMaxNumberOfDays(4);
		sut.setMinNumberOfDays(5);
	}
}
