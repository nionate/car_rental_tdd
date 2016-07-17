package cl.ubb.agil.service;

import cl.ubb.agil.dao.TimeConstraintDao;
import cl.ubb.agil.model.TimeConstraint;

public class TimeConstraintService {
	
	private TimeConstraintDao timeCDao;
	private TimeConstraint timeConstraint;
	
	public TimeConstraintService(TimeConstraintDao timeCDao){
		this.timeCDao = timeCDao;
	}
	
	public boolean canBooking(int minDays, int maxDays, int carTypeIdentifier, int customerCategoryIdentifier) {
		timeConstraint = timeCDao.getAllByCustomerCategoryAndCarType(customerCategoryIdentifier, carTypeIdentifier);
		if(minDays >= timeConstraint.getMinNumberOfDays() && maxDays <= timeConstraint.getMaxNumberOfDays()){
			return true;
		}
		return false;
	}

	

}
