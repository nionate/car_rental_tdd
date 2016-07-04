package cl.ubb.agil.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

import cl.ubb.agil.dao.CarSpecificationDao;
import cl.ubb.agil.model.CarSpecification;
import cl.ubb.agil.model.CarType;
import cl.ubb.agil.service.exception.EmptyListException;

@RunWith(MockitoJUnitRunner.class)
public class CarSpecificationServiceTest {
	
	private int identifier;
	
	@Mock
	private CarSpecificationDao csDao;
	
	@InjectMocks
	private CarSpecificationService csService;
	
	@Test
	public void getAllCarsByTypeShouldReturnListWithOneItem() throws EmptyListException{
		
		List<CarSpecification> list = new ArrayList<CarSpecification>();
		list.add(new CarSpecification(0, "chevrolet", "dmax", "2010"));
		
		identifier = 1;
		
		when(csDao.getAllCarsByType(1)).thenReturn(list);
		
		List<CarSpecification> result = csService.getAllCarsByType(identifier);
		
		assertThat(result, is(equalTo(list)));	
		assertEquals(1, result.size());
	}
	
	@Test
	public void getAllCarsByTypeShouldReturnListWithTwoItems() throws EmptyListException{
		
		List<CarSpecification> list = new ArrayList<CarSpecification>();
		list.add(new CarSpecification(0, "chevrolet", "dmax", "2010"));
		list.add(new CarSpecification(1, "toyota", "yaris", "2014"));
		
		identifier = 1;
		
		when(csDao.getAllCarsByType(1)).thenReturn(list);
		
		List<CarSpecification> result = csService.getAllCarsByType(identifier);
		
		assertThat(result, is(equalTo(list)));	
		assertEquals(2, result.size());
	}

	@Test(expected = EmptyListException.class)
	public void getAllCarsByTypeReturnAnEmptyListAndShouldThrowEmptyListException() throws EmptyListException{
		
		List<CarSpecification> list = new ArrayList<CarSpecification>();
		identifier = 1;
		
		when(csDao.getAllCarsByType(1)).thenReturn(list);
		
		List<CarSpecification> result = csService.getAllCarsByType(identifier);
		
	}
}
