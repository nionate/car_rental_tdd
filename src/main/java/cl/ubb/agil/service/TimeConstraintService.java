package cl.ubb.agil.service;

import cl.ubb.agil.dao.TimeConstraintDao;

public class TimeConstraintService {
	
	private TimeConstraintDao timeCDao;

	public int minimunTimeConstraintWithCartTypeAndCustomerCategory(int carType, String customerCategory) {
		if(carType == 2 && customerCategory == "1")
			return 2;
		else{
			return 5;
		}
		
	}

	public int maximunTimeConstraintWithCartTypeAndCustomerCategory(int carType, String customerCategory){
		if(carType == 2 && customerCategory == "1")
			return 10;
		else{
			return 30;
		}
	}

}
