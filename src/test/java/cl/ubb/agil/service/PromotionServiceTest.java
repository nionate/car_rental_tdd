package cl.ubb.agil.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import cl.ubb.agil.dao.PromotionDao;
import cl.ubb.agil.model.Promotion;
import cl.ubb.agil.service.exception.EmptyListException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)

public class PromotionServiceTest {
	
	private List<Promotion> promotions;
	
	@Mock
	private PromotionDao promotionDaoMock;
	
	@InjectMocks
	private PromotionService promotionServiceMock;
	
	@Before
	public void setup(){
		promotions = new ArrayList<Promotion>();	
	}

	@Test
	public void theIdCarTypeTwoReturnThePromotionWithThirtyPercent() throws ParseException, EmptyListException{
		int [] idsCarType = {2};
		Promotion promotion = new Promotion(1,"10/06/2016","20/06/2016",0.3,idsCarType);
		promotions.add(promotion);
		when(promotionDaoMock.getAllByCarType(2)).thenReturn(promotions);
		
		double discount = promotionServiceMock.getPromotionIfThereIs(2, "11/06/2016");
		
		assertThat(0.3,equalTo(discount));
		
	}
	
	@Test
	public void theIdCarTypeThreeReturnThePromotionWithFourtyPercent() throws ParseException, EmptyListException{
		int [] idsCarType = {3};
		Promotion promotion = new Promotion(2,"09/06/2016","20/06/2016",0.4,idsCarType);
		promotions.add(promotion);
		when(promotionDaoMock.getAllByCarType(3)).thenReturn(promotions);
		
		double discount = promotionServiceMock.getPromotionIfThereIs(3, "13/06/2016");
		
		assertThat(0.4,equalTo(discount));
	}
	
	@Test
	public void theIdCarTypeFourReturnThePromotionWithZeroPercentBecauseThereIsNotAPromotion() throws ParseException, EmptyListException{
		int [] idsCarType = {4};
		Promotion promotion = new Promotion(2,"09/06/2016","20/06/2016",0.4,idsCarType);
		promotions.add(promotion);
		when(promotionDaoMock.getAllByCarType(4)).thenReturn(promotions);
		
		double discount = promotionServiceMock.getPromotionIfThereIs(4, "07/08/2016");
		
		assertThat(0.0,equalTo(discount));	
	}

	
	@SuppressWarnings("unchecked")
	@Test(expected=EmptyListException.class)
	public void thePromotionListIsEmpty() throws ParseException, EmptyListException{
		when(promotionDaoMock.getAllByCarType(4)).thenThrow(EmptyListException.class);
		
		promotionServiceMock.getPromotionIfThereIs(4, "06/06/2016");
	}
	
	
}
