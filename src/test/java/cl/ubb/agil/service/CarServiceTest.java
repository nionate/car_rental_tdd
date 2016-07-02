package cl.ubb.agil.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cl.ubb.agil.dao.CarDao;
import cl.ubb.agil.dao.CarSpecificationDao;
import cl.ubb.agil.dao.CarTypeDao;
import cl.ubb.agil.dao.CustomerDao;
import cl.ubb.agil.dao.ExtraDao;
import cl.ubb.agil.dao.TimeConstraintDao;
import cl.ubb.agil.model.Car;
import cl.ubb.agil.model.CarSpecification;
import cl.ubb.agil.model.CarType;
import cl.ubb.agil.model.Customer;
import cl.ubb.agil.model.CustomerCategory;
import cl.ubb.agil.model.Extra;
import cl.ubb.agil.model.TimeConstraint;
import cl.ubb.agil.service.exception.EmptyListException;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {
	
	@Mock
	private CarDao carDao;
	@Mock
	private ExtraDao extraDao;
	@Mock
	private TimeConstraintDao constraintDao;
	@Mock
	private CustomerDao customerDao;
	@Mock
	private CarSpecificationDao csDao;
	
	@InjectMocks
	private CarService carService;
	
	@Test
	public void shouldReturnACarWithALicensePlate() throws EmptyListException{
		
		//key(id extras) , numero(cantidad)
		HashMap<Integer, Integer> extras = new HashMap<>();
		extras.put(1, 2);
		//Customer
		Customer customer = new Customer("184312107", "", "", "", 1);
		//Cars for test
		//TimeConstraint list
		List<TimeConstraint> tcList = new ArrayList<>();
		tcList.add(new TimeConstraint(2, 10, 2, 1));
		//Car list by branch
		List<Car> cListBranch = new ArrayList<>();
		cListBranch.add(new Car("ADWS-12", "rojo"));
		cListBranch.add(new Car("AAAA-12", "azul"));
		//Car list by specifications
		List<Car> cListBySpecification = new ArrayList<>();
		cListBySpecification.add(new Car("BBBB-12", "negro"));
		cListBySpecification.add(new Car("ADWS-12", "rojo"));
		cListBySpecification.add(new Car("AAAA-12", "azul"));
		//Car Specification list
		List<CarSpecification> csList = new ArrayList<>();
		csList.add(new CarSpecification(1, "chevrolet", "corsa", "2012"));
		
		
		CarType mock1 = mock(CarType.class);
		when(mock1.getIdentifier()).thenReturn(2);
		when(customerDao.getCustomer("184312107")).thenReturn(customer);
		when(constraintDao.getAll()).thenReturn(tcList);
		when(csDao.getAllCarsByType(2)).thenReturn(csList);
		when(carDao.getAllByBranchId(1)).thenReturn(cListBranch);
		when(carDao.getAllByCarSpecificationId(1)).thenReturn(cListBySpecification);
		

		
		Car resultado = carService.getACarIfPermitted("184312107", mock1, 1, "06-06-2016", 1, "08-06-2016", extras);
		
		assertEquals("ADWS-12", resultado.getLicensePlate());
	}
	
	@Test
	public void shouldReturnNullWichIndicatesThatNoCarFound() throws EmptyListException{
		
		//key(id extras) , numero(cantidad)
		HashMap<Integer, Integer> extras = new HashMap<>();
		extras.put(1, 2);
		//Customer
		Customer customer = new Customer("184312107", "", "", "", 1);
		//Cars for test
		//TimeConstraint list
		List<TimeConstraint> tcList = new ArrayList<>();
		tcList.add(new TimeConstraint(2, 10, 2, 1));
		//Car list by branch
		List<Car> cListBranch = new ArrayList<>();
		cListBranch.add(new Car("ADWS-12", "rojo"));
		//Car list by specifications
		List<Car> cListBySpecification = new ArrayList<>();
		cListBySpecification.add(new Car("BBBB-12", "negro"));

		//Car Specification list
		List<CarSpecification> csList = new ArrayList<>();
		csList.add(new CarSpecification(1, "chevrolet", "corsa", "2012"));
		
		
		CarType mock1 = mock(CarType.class);
		when(mock1.getIdentifier()).thenReturn(2);
		when(customerDao.getCustomer("184312107")).thenReturn(customer);
		when(constraintDao.getAll()).thenReturn(tcList);
		when(csDao.getAllCarsByType(2)).thenReturn(csList);
		when(carDao.getAllByBranchId(1)).thenReturn(cListBranch);
		when(carDao.getAllByCarSpecificationId(1)).thenReturn(cListBySpecification);
		
		Car resultado = carService.getACarIfPermitted("184312107", mock1, 1, "06-06-2016", 1, "08-06-2016", extras);
		
		assertNull(resultado);
	}
	
}
