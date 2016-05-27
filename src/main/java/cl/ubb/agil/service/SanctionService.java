package cl.ubb.agil.service;

import java.util.ArrayList;
import java.util.List;

import cl.ubb.agil.dao.SanctionDao;
import cl.ubb.agil.model.Sanction;

public class SanctionService {
	
	private SanctionDao sDao;

	public boolean searchUserWithSanction(String rut, String date) {
		
		List<Sanction> sanctions = sDao.getAll();		
		List<Sanction> CustomerSanctions = getSanctionCustomer(rut, sanctions);
		
		if(CustomerSanctions.isEmpty()){
			return false;
		}else{
			
			for(Sanction sanction : CustomerSanctions){
				String cRut = sanction.getCustomer().getRut();
				
				if(rut.equals(cRut) && verifyDays(sanction.getStartDate(), sanction.getDays(), date)){
					return true;
				}
			}
		}	
		return false;
	}

	
	// private methods
	private List<Sanction> getSanctionCustomer(String rutCustomer, List<Sanction> sanctions){
		List<Sanction> aux = new ArrayList<Sanction>();
		for(Sanction sanction : sanctions){
			if(rutCustomer.equals(sanction.getCustomer().getRut())){
				aux.add(sanction);
			}
		}
		return aux;
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
