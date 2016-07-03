package cl.ubb.agil.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cl.ubb.agil.dao.CarTypeDao;
import cl.ubb.agil.dao.ExtraDao;
import cl.ubb.agil.model.BookingExtra;
import cl.ubb.agil.model.CarType;
import cl.ubb.agil.model.Extra;
import cl.ubb.agil.service.exception.EmptyListException;

public class CarTypeService {
	
	private CarTypeDao carTypeDao;
	private ExtraDao extraDao;
	
	public CarTypeService(CarTypeDao carTypeDao, ExtraDao extraDao){
		this.carTypeDao = carTypeDao;
		this.extraDao = extraDao;
	}
	
	public List <CarType> getAllCarTypes() throws EmptyListException{
		List <CarType> carsTypes = new ArrayList <CarType>();
		carsTypes = carTypeDao.getAllCarTypes();
		if(carsTypes.isEmpty()){
			throw new EmptyListException();
		}else
			return carsTypes;
	}

	public int getPrice(String startDate, String endDate, int identifier, List<BookingExtra> extras) throws ParseException {
		int total_price = 0;
		int diff_days = diffDays(stringToDate(endDate), stringToDate(startDate));
		CarType type = carTypeDao.getCarType(identifier);
		
		for(BookingExtra extra : extras){
			Extra extra1 = extraDao.get(extra.getExtraId());
			total_price += ((extra1.getDailyPrice() * extra.getNumber()) * diff_days);
		}
		
		total_price += type.getDailyPrice() * diff_days;
		
		return total_price;
	}
	
	public int diffDays(Date endDate, Date startDate) {
		long ms_diff = endDate.getTime() - startDate.getTime();
		long days = ms_diff / (1000 * 60 * 60 * 24);
		return (int) days;
	}
	
	public Date stringToDate(String dateString) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		date = formatter.parse(dateString);
		return date;
	}
}
