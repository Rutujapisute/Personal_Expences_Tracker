package com.example.Expences_TrackerUsingJWT.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Expences_TrackerUsingJWT.Dao.TotalExpencesDao;

@Service
public class TotalExpencesService {

	@Autowired
	private TotalExpencesDao totalExpencesDao;
	
	public String todaysExpences(int uid)
	{
		String total=totalExpencesDao.todaysExpences(uid);
		return total;
	}
	
	 public String monthlyExpences(int uid) {
		 String total=totalExpencesDao.monthlyExpences(uid);
		 return total;
	 }
	 
	 public String yearlyExpences(int uid)
	 {
		 String total=totalExpencesDao.yearlyExpences(uid);
		 return total;
	 }
	 
	 
	  public String dailyFoodExpences(int uid) {
		  String total=totalExpencesDao.dailyFoodExpences(uid);
			 return total;
	  }
	  
	  public String dailyTravelExpences(int uid) {
		  String total=totalExpencesDao.dailyTravelExpences(uid);
			 return total;

	  }
	  
	  public String dailyShoppingExpences(int uid) {
		  String total=totalExpencesDao.dailyShoppingExpences(uid);
			 return total;

	  }
	  
	  public String dailyOthersExpences(int uid) {
		  String total=totalExpencesDao.dailyOthersExpences(uid);
			 return total;

	  }
	  
	  public String monthlyCategoryExpenses(int uid, int year, int month, String categoryName) 

		  {
			  String total=totalExpencesDao.monthlyCategoryExpenses(uid,year,month,categoryName);
				 return total;

		  }
		  
		  public String yearlyCategoryExpenses(int uid, int year, String categoryName) 
			  {
				  String total=totalExpencesDao.yearlyCategoryExpenses(uid,year,categoryName);
					 return total;

			  }

		  

}