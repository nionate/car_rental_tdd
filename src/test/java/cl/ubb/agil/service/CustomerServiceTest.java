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
	
	private static final String C_RUT = "184312107";
	private static final String C_NAME = "Nicolas Oñate";
	private static final String C_PHONE = "78343505";
	private static final String C_EMAIL = "nionate@alumnos.ubiobio.cl";
	
	@Test
	public void registerCustomerWithRut184312107(){
		
		when(cMock.getRut()).thenReturn(C_RUT);
		when(cMock.getName()).thenReturn("");
		when(cMock.getCellPhone()).thenReturn("");
		when(cMock.getEmail()).thenReturn("");
		when(cMock.getCustomerCategoryIdentifier()).thenReturn(-1);

		cService.registerCustomer(cMock);
		
		verify(cDao).registerCustomer(C_RUT, "", "", "", -1);
	}
	
	@Test
	public void registerCustomerWithRutAndName(){
		
		when(cMock.getRut()).thenReturn(C_RUT);
		when(cMock.getName()).thenReturn(C_NAME);
		when(cMock.getCellPhone()).thenReturn("");
		when(cMock.getEmail()).thenReturn("");
		when(cMock.getCustomerCategoryIdentifier()).thenReturn(-1);
		
		cService.registerCustomer(cMock);
		
		verify(cDao).registerCustomer(C_RUT, C_NAME, "", "", -1);
	}
	
	@Test
	public void registerCustomerWithRutNameAndCellPhone(){
		
		when(cMock.getRut()).thenReturn(C_RUT);
		when(cMock.getName()).thenReturn(C_NAME);
		when(cMock.getCellPhone()).thenReturn(C_PHONE);
		when(cMock.getEmail()).thenReturn("");
		when(cMock.getCustomerCategoryIdentifier()).thenReturn(-1);
		
		cService.registerCustomer(cMock);
		
		verify(cDao).registerCustomer(C_RUT, C_NAME, C_PHONE, "", -1);
	}
	
	@Test
	public void registerCustomerWithRutNameCellPhoneAndEmail(){
		
		when(cMock.getRut()).thenReturn(C_RUT);
		when(cMock.getName()).thenReturn(C_NAME);
		when(cMock.getCellPhone()).thenReturn(C_PHONE);
		when(cMock.getEmail()).thenReturn(C_EMAIL);
		when(cMock.getCustomerCategoryIdentifier()).thenReturn(-1);
		
		cService.registerCustomer(cMock);
		
		verify(cDao).registerCustomer(C_RUT, C_NAME, C_PHONE, C_EMAIL, -1);
	}
	
	@Test
	public void registerCustomerWithCustomerCategory(){
		
		when(cMock.getRut()).thenReturn(C_RUT);
		when(cMock.getName()).thenReturn(C_NAME);
		when(cMock.getCellPhone()).thenReturn(C_PHONE);
		when(cMock.getEmail()).thenReturn(C_EMAIL);
		when(cMock.getCustomerCategoryIdentifier()).thenReturn(1);
		
		cService.registerCustomer(cMock);
		
		verify(cDao).registerCustomer(C_RUT, C_NAME, C_PHONE, C_EMAIL, 1);
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
}
