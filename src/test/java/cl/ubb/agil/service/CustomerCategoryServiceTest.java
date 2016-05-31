package cl.ubb.agil.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import cl.ubb.agil.dao.CustomerCategoryDao;
import cl.ubb.agil.model.CustomerCategory;
import cl.ubb.agil.service.exception.EmptyListException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CustomerCategoryServiceTest {
	
	@Mock
	private CustomerCategoryDao ccDao;
	@Mock
	private CustomerCategory ccMock1, ccMock2;
	
	@InjectMocks
	private CustomerCategoryService ccService;
	
	@Test
	public void categoryListHasOneElement() throws EmptyListException{
		
		List<CustomerCategory> categories = new ArrayList<CustomerCategory>();
		categories.add(ccMock1);
		
		when(ccDao.getAllCustomerCategories()).thenReturn(categories);
		
		List<CustomerCategory> result = ccService.getAllCategories();
		
		assertEquals(1, result.size());
		
	}
	
	@Test
	public void categoryListHasTwoElement() throws EmptyListException{
		
		List<CustomerCategory> categories = new ArrayList<CustomerCategory>();
		categories.add(ccMock1);
		categories.add(ccMock2);
		
		when(ccDao.getAllCustomerCategories()).thenReturn(categories);
		
		List<CustomerCategory> result = ccService.getAllCategories();
		
		assertEquals(2, result.size());
		
	}
	
	@Test(expected = EmptyListException.class)
	public void categoryListIsEmpty() throws EmptyListException{
		
		List<CustomerCategory> categories = new ArrayList<CustomerCategory>();
		
		when(ccDao.getAllCustomerCategories()).thenReturn(categories);
		
		List<CustomerCategory> result = ccService.getAllCategories();
	}


}
