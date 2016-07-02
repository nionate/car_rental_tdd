package cl.ubb.agil.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cl.ubb.agil.dao.CarTypeDao;
import cl.ubb.agil.model.CarType;
import cl.ubb.agil.model.Extra;
import cl.ubb.agil.service.exception.EmptyListException;

public class CarTypeService {
	
	private CarTypeDao carTypeDao;
	
	public List <CarType> getAllCarTypes() throws EmptyListException{
		List <CarType> carsTypes = new ArrayList <CarType>();
		carsTypes = carTypeDao.getAllCarTypes();
		if(carsTypes.isEmpty()){
			throw new EmptyListException();
		}else
			return carsTypes;
	}

	public int getPrice(String startDate, String endDate, int identifier, List<Extra> extras) {
		int total_price = 0;
		int diff_days = diffDays(stringToDate(endDate), stringToDate(startDate));
		CarType type = carTypeDao.getCarType(identifier);
		
		for(Extra extra : extras){
			total_price += extra.getDailyPrice() * diff_days;
		}
		
		total_price += type.getDailyPrice() * diff_days;
		
		return total_price;
	}
	
	public int diffDays(Date endDate, Date startDate) {
		long ms_diff = endDate.getTime() - startDate.getTime();
		long days = ms_diff / (1000 * 60 * 60 * 24);
		return (int) days;
	}
	
	public Date stringToDate(String dateString){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;

		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
