package cl.ubb.agil.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import cl.ubb.agil.dao.SanctionDao;
import cl.ubb.agil.model.Customer;
import cl.ubb.agil.model.Sanction;


@RunWith(MockitoJUnitRunner.class)
public class SanctionServiceTest {
	
	@Mock
	private SanctionDao sdao;
	@Mock
	private Customer cMock;
	@Mock
	private Sanction sMock;
	
	@InjectMocks
	private SanctionService sService;
	
	private List<Sanction> sanctions;
	
	@Before
	public void setup(){
		sanctions = new ArrayList<Sanction>();
	}
	
	
	@Test
	public void clientHasOneSanctionsDated12052016(){
		
		sanctions.add(sMock);
		
		when(sMock.getStartDate()).thenReturn("10/05/2016");
		when(sMock.getDays()).thenReturn(5);
		when(sMock.getCustomer()).thenReturn(cMock);
		when(cMock.getRut()).thenReturn("184312107");
		when(sdao.getAllByCostumer(cMock.getRut())).thenReturn(sanctions);
		
		boolean result = sService.searchUserWithSanction("184312107", "12/05/2016");
		
		assertEquals(true, result);
	}
	
	@Test
	public void theDateIsBeforeToTheSanction(){
		
		sanctions.add(sMock);
		
		when(sMock.getStartDate()).thenReturn("01/06/2016");
		when(sMock.getDays()).thenReturn(2);
		when(sMock.getCustomer()).thenReturn(cMock);
		when(cMock.getRut()).thenReturn("184312107");
		when(sdao.getAllByCostumer(cMock.getRut())).thenReturn(sanctions);
		
		boolean result = sService.searchUserWithSanction("184312107", "30/05/2016");
		
		assertEquals(false, result);
		
	}
	
	@Test
	public void theDateIsAfterToTheSanction(){
		
		sanctions.add(sMock);
		
		when(sMock.getStartDate()).thenReturn("01/06/2016");
		when(sMock.getDays()).thenReturn(3);
		when(sMock.getCustomer()).thenReturn(cMock);
		when(cMock.getRut()).thenReturn("184313008");
		when(sdao.getAllByCostumer(cMock.getRut())).thenReturn(sanctions);
		
		boolean result = sService.searchUserWithSanction("184313008", "05/06/2016");
		
		assertEquals(false, result);
	}
	
	@Test
	public void clientHasNoSanctions(){
		
		when(cMock.getRut()).thenReturn("184313008");
		when(sdao.getAllByCostumer(cMock.getRut())).thenReturn(sanctions);
		
		boolean result = sService.searchUserWithSanction("184313008", "05/07/2016");
		
		assertEquals(false, result);
	}
}
