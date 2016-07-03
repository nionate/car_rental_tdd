package cl.ubb.agil.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import cl.ubb.agil.dao.PromotionDao;
import cl.ubb.agil.model.Promotion;
import cl.ubb.agil.service.exception.EmptyListException;

public class PromotionService {
	
	private PromotionDao promotionDao;
	
	public double getPromotionIfThereIs(int idCarType, String fecha) throws ParseException, EmptyListException{
		double discount=0.0;
		boolean found = false;
		Date datePromotion = convertDate(fecha);
		List<Promotion> promotions = new ArrayList<Promotion>();
		promotions=promotionDao.getAllByCarType(idCarType);
		if(promotions.isEmpty()){
			throw new EmptyListException();
		}
		else{
			for(Promotion promotion : promotions){
				for(int i=0; i<promotion.getIdCarType().length;i++){
					int [] ids = promotion.getIdCarType();
					if(ids[i]==idCarType){
						Date dateStart = substractOneDay(convertDate(promotion.getStartDate()));
						Date dateEnd = addOneDay(convertDate(promotion.getEndDate()));
						if(datePromotion.after(dateStart) && datePromotion.before(dateEnd)){
							discount = promotion.getDiscount();
							found=true;
							break;
						}
					}
				}
				if(found=true)
					break;
			}
		}
		return discount;	
	}
	
	
	private Date convertDate(String stringDate) throws ParseException{
		DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		Date convertedDate = null;
		convertedDate = date.parse(stringDate);
		return convertedDate;
		
	}
	
	private Date substractOneDay(Date date){
		Date newDate;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		newDate = calendar.getTime();
		return newDate;
	}
	
	private Date addOneDay(Date date){
		Date newDate;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		newDate = calendar.getTime();
		return newDate;
	}
		

}
