package cl.ubb.agil.service;

import java.util.List;

import cl.ubb.agil.dao.SanctionDao;
import cl.ubb.agil.model.Sanction;

public class SanctionService {
	
	private SanctionDao sDao;

	public boolean searchUserWithSanction(String rut, String date) {
		
		List<Sanction> sanctions = sDao.getAllByCostumer(rut);		
		
		if(sanctions.isEmpty()){
			return false;
		}else{
			
			for(Sanction sanction : sanctions){
				String cRut = sanction.getCustomer().getRut();
				
				if(rut.equals(cRut) && verifyDays(sanction.getStartDate(), sanction.getDays(), date)){
					return true;
				}
			}
		}	
		return false;
	}
	
	private boolean verifyDays(String startDate, int days, String date){
		
		boolean result = false;
		
		String[] dateFormat = date.split("/");
		int day = Integer.parseInt(dateFormat[0]);
		int month = Integer.parseInt(dateFormat[1]);
		int year = Integer.parseInt(dateFormat[2]);
		
		String[] startDateFormat = startDate.split("/");
		int startDay = Integer.parseInt(startDateFormat[0]);
		int startMonth = Integer.parseInt(startDateFormat[1]);
		int startYear = Integer.parseInt(startDateFormat[2]);
		
		if(year == startYear && month == startMonth){
			if(day >= startDay && day <= (startDay + days)){
				result = true;
			}
		}
				
		return result;
	}

}
