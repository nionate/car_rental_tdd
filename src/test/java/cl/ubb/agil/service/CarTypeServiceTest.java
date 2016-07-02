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
import cl.ubb.agil.dao.ExtraDao;
import cl.ubb.agil.model.CarType;
import cl.ubb.agil.model.Extra;
import cl.ubb.agil.model.TimeConstraint;
import cl.ubb.agil.service.exception.EmptyListException;

@RunWith(MockitoJUnitRunner.class)
public class CarTypeServiceTest {

	@Mock
	private CarTypeDao carTypeDao;
	@Mock
	private ExtraDao extraDao;

	private CarType carType, carTypeAux;

	private List<CarType> listCarsTypes;
	
	private TimeConstraint timeConstraint;

	@InjectMocks
	private CarTypeService carTypeService;

	@Before
	public void setup() {
		listCarsTypes = new ArrayList<CarType>();
		carType = new CarType(54321, "sports", "automatic", "diesel", "yes", 2, 2, 40000);
	}

	@Test
	public void getAllCarsTypesReturnListWithOneCarType() throws EmptyListException {
		listCarsTypes.add(carType);
		when(carTypeDao.getAllCarTypes()).thenReturn(listCarsTypes);

		assertEquals(listCarsTypes, carTypeService.getAllCarTypes());
		assertEquals(1, carTypeService.getAllCarTypes().size());

	}

	@Test
	public void getAllCarsTypesReturnListWithTwoCarsTypes() throws EmptyListException {
		listCarsTypes.add(carType);
		carTypeAux = new CarType(898989, "familiar", "manual", "gas",  "yes", 6, 5, 30000);
		listCarsTypes.add(carTypeAux);
		when(carTypeDao.getAllCarTypes()).thenReturn(listCarsTypes);

		assertEquals(2, carTypeService.getAllCarTypes().size());
	}
	
	@Test(expected = EmptyListException.class)
	public void getAllCarsTypesReturnEmptyList() throws EmptyListException {
		when(carTypeDao.getAllCarTypes()).thenReturn(listCarsTypes);

		carTypeService.getAllCarTypes();
	}
		
	@Test
	public void shouldReturn39000WhenACarTypeWithId2IsConsulted(){
		
		String startDate = "05/05/2016";
		String endDate = "08/05/2016";
		CarType carType = new CarType(1, "", "automatic", "diesel", "", 5, 5, 12000);
		
		List<Extra> extras = new ArrayList<>();
		extras.add(new Extra(2, "", "", 1000));
		
		when(carTypeDao.getCarType(carType.getIdentifier())).thenReturn(carType);
		
		int price = carTypeService.getPrice(startDate, endDate, carType.getIdentifier(), extras);
		
		assertEquals(39000, price);
	}

}
