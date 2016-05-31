package cl.ubb.agil.service;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import cl.ubb.agil.dao.ExtraDao;
import cl.ubb.agil.model.Extra;
import cl.ubb.agil.service.exception.EmptyListException;

@RunWith(MockitoJUnitRunner.class)
public class ExtraServiceTest {
	@Mock
	private ExtraDao extraDao;

	@InjectMocks
	private ExtraService extraService;

	@Test
	public void sizeOfGetAllExtrasIsOneTest() throws EmptyListException {
		/*Arrange*/
		Extra extra = new Extra(1111,"Baby seat", "blue chair", 8000);
		ArrayList<Extra> extras = new ArrayList<Extra>();
		extras.add(extra);
		when(extraDao.getAll()).thenReturn(extras);
		
		/*Act*/
		ArrayList<Extra> listExtra = extraService.getAll();
		
		/*Assert*/
		assertThat(listExtra, is(equalTo(extras)));
	
	}
	
	@Test
	public void sizeOfGetAllExtrasIstwoTest() throws EmptyListException {
		/*Arrange*/
		Extra extra = new Extra(1111,"Baby seat", "blue chair", 8000);
		Extra extra1 = new Extra(1112,"GPS", "This is a GPS", 8000);
		ArrayList<Extra> extras = new ArrayList<Extra>();
		extras.add(extra);
		extras.add(extra1);
		when(extraDao.getAll()).thenReturn(extras);
		
		/*Act*/
		ArrayList<Extra> listExtra = extraService.getAll();
		
		/*Assert*/
		assertThat(listExtra, is(equalTo(extras)));
	}

	@Test(expected = EmptyListException.class)
	public void GetAllExtrasReturnEmptyTest() throws EmptyListException {
		/*Arrange*/
		ArrayList<Extra> extras = new ArrayList<Extra>();
		when(extraDao.getAll()).thenReturn(extras);
		
		/*Act*/
		extraService.getAll();

	}

	

}
