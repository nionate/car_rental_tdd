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
import cl.ubb.agil.service.exception.CreateException;
import cl.ubb.agil.service.exception.ReadErrorException;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
	
	@Mock
	private CustomerDao cDao;

	private Customer customer;
	
	@InjectMocks
	private CustomerService cService;
	
	private static final String C_RUT = "184312107";
	private static final String C_NAME = "Nicolas";
	private static final String C_PHONE = "78343505";
	private static final String C_EMAIL = "nionate@gmail.com";
	private CustomerCategory cc = new CustomerCategory(0, "","");
	
	@Test
	public void registerCustomerTest() throws CreateException{
		
		customer = new Customer(C_RUT, C_NAME, C_PHONE, C_EMAIL, cc);
		
		cService.registerCustomer(customer);
		
		verify(cDao).create(customer);
	}
	
	@Test(expected = CreateException.class)
	public void resgisterCustomerWithIncorrectRutShouldThrowCreateException() throws CreateException{
		
		String wrongRut = "184312106";
		customer = new Customer(wrongRut, C_NAME, C_PHONE, C_EMAIL, cc);
		
		cService.registerCustomer(customer);
	}
	
	@Test(expected = CreateException.class)
	public void registerCustomerWithIncorrectEmailShouldThrowCreateException() throws CreateException{
		
		String wrongEmail = "wrongemail@com";
		customer = new Customer(C_RUT, C_NAME, C_PHONE, wrongEmail, cc);
			
		cService.registerCustomer(customer);	
	}
	
	@Test(expected = CreateException.class)
	public void registerCustomerWithIncorrectCellPhoneShouldThrowCreateException() throws CreateException{
		
		customer = new Customer(C_RUT, C_NAME, "123", C_EMAIL, cc);
		
		cService.registerCustomer(customer);
	}
	
	@Test(expected = CreateException.class)
	public void registerCustomerWithOutCustomerCategoryShouldThrowCreateException() throws CreateException{
		
		customer = new Customer(C_RUT, C_NAME, C_PHONE, C_EMAIL, null);
		
		cService.registerCustomer(customer);
	}

	@Test
	public void customerIsRegistered() throws CreateException{
		
		customer = new Customer(C_RUT, C_NAME, C_PHONE, C_EMAIL, cc);		
		when(cDao.getCustomer(C_RUT)).thenReturn(customer);
		
		cService.registerCustomer(customer);
		
		boolean result = cService.isRegistered(customer.getRut());
		
		assertEquals(true, result);
	}
	
	@Test
	public void customerIsNotRegistered(){
		
		boolean result = cService.isRegistered(C_RUT);
		
		assertEquals(false, result);
	}
	
	@Test
	public void customerWithRut184312107IsAPerson() throws ReadErrorException{
		
		CustomerCategory cCategory = new CustomerCategory(1, "Person","1235");
		customer = new Customer(C_RUT, C_NAME, C_PHONE, C_EMAIL, cCategory);			
		when(cDao.getCustomer(C_RUT)).thenReturn(customer);
		
		CustomerCategory result = cService.getCustomerCategory(customer.getRut());
		
		assertEquals("Person", result.getName());
	}
	
	@Test
	public void customerWithRut184003008IsAnEnterprise() throws ReadErrorException{
		
		CustomerCategory cCategory = new CustomerCategory(2, "Enterprise","1234");
		customer = new Customer("184003008", C_NAME, C_PHONE, C_EMAIL, cCategory);	
		when(cDao.getCustomer("184003008")).thenReturn(customer);
		
		CustomerCategory result = cService.getCustomerCategory("184003008");
		
		assertEquals("Enterprise", result.getName());
		
	}
	
	@Test(expected = ReadErrorException.class)
	public void customerWithRut184312107IsNotRegisteredAndNotHaveACategory() throws ReadErrorException{
			
		CustomerCategory result = cService.getCustomerCategory(C_RUT);
		
	}
	
}
