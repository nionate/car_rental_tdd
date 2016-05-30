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

@RunWith(MockitoJUnitRunner.class)
public class CarTypeServiceTest {
	
	@Mock
	private	CarTypeDao carTypeDao;
	
	@Mock
	private CarType carType;
	
	private List<CarType> cars;
	
	@InjectMocks
	private CarTypeService carTypeService;
	
	@Before
	public void setup(){
		cars = new ArrayList<CarType>();
		
	}
	
	@Test
	public void getAllCarsTypesReturnListWithOneCar(){
		cars.add(carType);
		when(carType.getIdentifier()).thenReturn(1234);
		when(carType.getName()).thenReturn("sports");
		when(carType.getTransmisionType()).thenReturn("automatic");
		when(carType.getFuelType()).thenReturn("diesel");
		when(carType.getBags()).thenReturn(2);
		when(carType.getPassengers()).thenReturn(2);
		when(carType.getDailyPrice()).thenReturn(40000);
		when(carTypeDao.getAllCarTypes()).thenReturn(cars);
		
		assertEquals(cars,carTypeService.getAllCarTypes());
		assertEquals(1,carTypeService.getAllCarTypes().size());
		
	}

	


}
