package cl.ubb.agil.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import cl.ubb.agil.dao.CustomerCategoryDao;
import cl.ubb.agil.model.CustomerCategory;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CustomerCategoryServiceTest {
	
	@Mock
	private CustomerCategoryDao ccDao;
	@Mock
	private CustomerCategory ccMock;
	
	@InjectMocks
	private CustomerCategoryService ccService;
	
	@Test
	public void categoryListHasOneElement(){
		
		List<CustomerCategory> categories = new ArrayList<CustomerCategory>();
		categories.add(ccMock);
		
		when(ccDao.getAllCustomerCategories()).thenReturn(categories);
		
		List<CustomerCategory> result = ccService.getAllCategories();
		
		assertEquals(1, result.size());
		
	}

}
