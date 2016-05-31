package cl.ubb.agil.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import cl.ubb.agil.dao.CarDao;
import cl.ubb.agil.dao.CarTypeDao;
import cl.ubb.agil.model.Car;
import cl.ubb.agil.model.CarSpecification;
import cl.ubb.agil.model.CarType;
import cl.ubb.agil.service.exception.EmptyListException;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {
	
	@Mock
	private CarDao carDaoMk;
	
	private Car car;
	
	@InjectMocks
	private CarService carServiceMk;
	
	/*
	@Test
	public void getAllCarsByTipeReturnsCarsWithTheSameId(){
		CarType carType = new CarType(4321,"familiar","manual","gas", "no", 6, 5, 30000);
		CarSpecification carSpecification = new CarSpecification(787878,"nissan","v16",1998,carType);
		car = new Car("RNHD67","white",carSpecification);
		when(carDaoMk.getAllCarsByTipes(1234)).thenReturn(carSpecification);
	}
	*/
}
