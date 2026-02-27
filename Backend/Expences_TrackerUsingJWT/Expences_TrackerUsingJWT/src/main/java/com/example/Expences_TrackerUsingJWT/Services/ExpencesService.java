package com.example.Expences_TrackerUsingJWT.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Expences_TrackerUsingJWT.Dao.ExpencesDao;
import com.example.Expences_TrackerUsingJWT.Model.expences;

@Service
public class ExpencesService {

	@Autowired
	private ExpencesDao  expencesDao;
	
	public List<expences> getExpences(int uid)
	{
		 List<expences> list=expencesDao.getExpences(uid)	;
		 return list;
	}
public 	List<expences> getDailyExpences(int uid)
	{
		List<expences> list =expencesDao.getDailyExpences(uid);
		return list;
	}
public String addExpences(expences exp)
{
	String msg=expencesDao.addExpences(exp);
	return msg;
	
}
public List<expences> getMonthlyExpences(int uid)
{
	List<expences> list =expencesDao.getMonthlyExpences(uid);
	return list;
}

public List<expences> getYearlyExpences(int uid)
{
	List<expences> list =expencesDao.getYearlyExpences(uid);
	return list;
}

public List<expences> getMonthlyExpensesForSecondMenu(int uid,int yyyy,int mm)
{
	List<expences> list =expencesDao.getMonthlyExpensesForSecondMenu(uid,yyyy,mm);
	return list;
}
public List<expences> getYearlyExpensesList(int uid, int year) {
    return expencesDao.getYearlyExpensesList(uid, year);
}


}
