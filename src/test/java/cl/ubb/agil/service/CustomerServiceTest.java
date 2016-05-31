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
import cl.ubb.agil.model.CustomerCategory;
import cl.ubb.agil.service.exception.ReadErrorException;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
	
	@Mock
	private CustomerDao cDao;
	@Mock
	private Customer cMock;
	
	@InjectMocks
	private CustomerService cService;
	
	private static final String C_RUT = "184312107";
	private static final String C_NAME = "Nicolas O�ate";
	private static final String C_PHONE = "78343505";
	private static final String C_EMAIL = "nionate@alumnos.ubiobio.cl";
	
	@Test
	public void registerCustomerWithRut184312107(){
		
		when(cMock.getRut()).thenReturn(C_RUT);
		when(cMock.getName()).thenReturn("");
		when(cMock.getCellPhone()).thenReturn("");
		when(cMock.getEmail()).thenReturn("");
		when(cMock.getCustomerCategory()).thenReturn(null);

		cService.registerCustomer(cMock);
		
		verify(cDao).create(C_RUT, "", "", "", null);
	}
	
	@Test
	public void registerCustomerWithRutAndName(){
		
		when(cMock.getRut()).thenReturn(C_RUT);
		when(cMock.getName()).thenReturn(C_NAME);
		when(cMock.getCellPhone()).thenReturn("");
		when(cMock.getEmail()).thenReturn("");
		when(cMock.getCustomerCategory()).thenReturn(null);
		
		cService.registerCustomer(cMock);
		
		verify(cDao).create(C_RUT, C_NAME, "", "", null);
	}
	
	@Test
	public void registerCustomerWithRutNameAndCellPhone(){
		
		when(cMock.getRut()).thenReturn(C_RUT);
		when(cMock.getName()).thenReturn(C_NAME);
		when(cMock.getCellPhone()).thenReturn(C_PHONE);
		when(cMock.getEmail()).thenReturn("");
		when(cMock.getCustomerCategory()).thenReturn(null);
		
		cService.registerCustomer(cMock);
		
		verify(cDao).create(C_RUT, C_NAME, C_PHONE, "", null);
	}
	
	@Test
	public void registerCustomerWithRutNameCellPhoneAndEmail(){
		
		when(cMock.getRut()).thenReturn(C_RUT);
		when(cMock.getName()).thenReturn(C_NAME);
		when(cMock.getCellPhone()).thenReturn(C_PHONE);
		when(cMock.getEmail()).thenReturn(C_EMAIL);
		when(cMock.getCustomerCategory()).thenReturn(null);
		
		cService.registerCustomer(cMock);
		
		verify(cDao).create(C_RUT, C_NAME, C_PHONE, C_EMAIL, null);
	}
	
	@Test
	public void registerCustomerWithCustomerCategory(){
		
		CustomerCategory cCategory = new CustomerCategory(1, "Person");
		
		when(cMock.getRut()).thenReturn(C_RUT);
		when(cMock.getName()).thenReturn(C_NAME);
		when(cMock.getCellPhone()).thenReturn(C_PHONE);
		when(cMock.getEmail()).thenReturn(C_EMAIL);
		when(cMock.getCustomerCategory()).thenReturn(cCategory);
		
		cService.registerCustomer(cMock);
		
		verify(cDao).create(C_RUT, C_NAME, C_PHONE, C_EMAIL, cCategory);
	}

	@Test
	public void customerIsRegistered(){
		
		when(cMock.getRut()).thenReturn(C_RUT);
		when(cDao.getCustomer(C_RUT)).thenReturn(cMock);
		
		cService.registerCustomer(cMock);
		
		boolean result = cService.isRegistered(C_RUT);
		
		assertEquals(true, result);
	}
	
	@Test
	public void customerIsNotRegistered(){
		
		boolean result = cService.isRegistered(C_RUT);
		
		assertEquals(false, result);
	}
	
	@Test
	public void customerWithRut184312107IsAPerson() throws ReadErrorException{
		
		CustomerCategory cCategory = new CustomerCategory(1, "Person");
		
		when(cDao.getCustomer("184312107")).thenReturn(cMock);
		when(cMock.getCustomerCategory()).thenReturn(cCategory);
		
		CustomerCategory result = cService.getCustomerCategory("184312107");
		
		assertEquals("Person", result.getName());
	}
	
	@Test
	public void customerWithRut184003008IsAnEnterprise() throws ReadErrorException{
		
		CustomerCategory cCategory = new CustomerCategory(2, "Enterprise");
		
		when(cDao.getCustomer("184003008")).thenReturn(cMock);
		when(cMock.getCustomerCategory()).thenReturn(cCategory);
		
		CustomerCategory result = cService.getCustomerCategory("184003008");
		
		assertEquals("Enterprise", result.getName());
		
	}
	
	@Test(expected = ReadErrorException.class)
	public void customerWithRut184312107IsNotRegisteredAndNotHaveACategory() throws ReadErrorException{
			
		CustomerCategory result = cService.getCustomerCategory("184312107");
		
	}
	
}
