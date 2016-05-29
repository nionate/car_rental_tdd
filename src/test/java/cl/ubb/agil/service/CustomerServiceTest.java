package cl.ubb.agil.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import cl.ubb.agil.dao.CustomerDao;
import cl.ubb.agil.model.Customer;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
	
	@Mock
	private CustomerDao cDao;
	@Mock
	private Customer cMock;
	
	@InjectMocks
	private CustomerService cService;
	
	@Test
	public void registerCustomerWithRut184312107(){
		
		when(cMock.getRut()).thenReturn("184312107");
		when(cMock.getName()).thenReturn("");

		cService.registerCustomer(cMock);
		
		verify(cDao).registerCustomer("184312107", "");
	}
	
	@Test
	public void registerCustomerWithRutAndName(){
		
		when(cMock.getRut()).thenReturn("184312107");
		when(cMock.getName()).thenReturn("Nicolas Oñate");
		
		cService.registerCustomer(cMock);
		
		verify(cDao).registerCustomer("184312107", "Nicolas Oñate");
	}
}
