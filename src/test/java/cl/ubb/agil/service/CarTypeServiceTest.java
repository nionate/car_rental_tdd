package cl.ubb.agil.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import cl.ubb.agil.dao.CarTypeDao;
import cl.ubb.agil.model.CarType;
import cl.ubb.agil.service.exception.EmptyListException;

@RunWith(MockitoJUnitRunner.class)
public class CarTypeServiceTest {
	
	@Mock
	private	CarTypeDao carTypeDao;
	
	@Mock
	private CarType carType, carTypeAux;
	
	private List<CarType> listCarsTypes;
	
	@InjectMocks
	private CarTypeService carTypeService;
	
	@Before
	public void setup(){
		listCarsTypes = new ArrayList<CarType>();
		
	}
	
	@Test
	public void getAllCarsTypesReturnListWithOneCarType() throws EmptyListException{
		listCarsTypes.add(carType);
		when(carType.getIdentifier()).thenReturn(1234);
		when(carType.getName()).thenReturn("sports");
		when(carType.getTransmisionType()).thenReturn("automatic");
		when(carType.getFuelType()).thenReturn("diesel");
		when(carType.getBags()).thenReturn(2);
		when(carType.getPassengers()).thenReturn(2);
		when(carType.getDailyPrice()).thenReturn(40000);
		when(carTypeDao.getAllCarTypes()).thenReturn(listCarsTypes);
		
		assertEquals(listCarsTypes,carTypeService.getAllCarTypes());
		assertEquals(1,carTypeService.getAllCarTypes().size());
		
	}
	
	@Test
	public void getAllCarsTypesReturnListWithTwoCarsTypes() throws EmptyListException{
		listCarsTypes.add(carType);
		listCarsTypes.add(carTypeAux);
		when(carTypeDao.getAllCarTypes()).thenReturn(listCarsTypes);
		
		assertEquals(2,carTypeService.getAllCarTypes().size());
	}
	
	@Test(expected=EmptyListException.class)
	public void getAllCarsTypesReturnEmptyList() throws EmptyListException{
		when(carTypeDao.getAllCarTypes()).thenReturn(listCarsTypes);
		
		carTypeService.getAllCarTypes();
	}

	


}
