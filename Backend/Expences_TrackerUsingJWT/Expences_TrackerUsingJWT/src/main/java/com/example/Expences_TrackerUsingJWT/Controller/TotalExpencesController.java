package com.example.Expences_TrackerUsingJWT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Expences_TrackerUsingJWT.Dao.Dao;
import com.example.Expences_TrackerUsingJWT.Model.MonthRequest;
import com.example.Expences_TrackerUsingJWT.Model.users;
import com.example.Expences_TrackerUsingJWT.Services.TotalExpencesService;

@RestController
public class TotalExpencesController {
	
	@Autowired
	private TotalExpencesService totalExpencesService;
	 @Autowired
   private Dao dao; 
	 
	@GetMapping("dailyExpences")
	public String todaysExpences()
	{
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String email = authentication.getName();  

	       
	        users user = dao.getUserByEmail(email);
		String total=totalExpencesService.todaysExpences(user.getUid());
		return total;
	}
	
	@GetMapping("monthlyExpences")
	public String monthlyExpences()
	{
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String email = authentication.getName();  

	       
	        users user = dao.getUserByEmail(email);
		String total=totalExpencesService.monthlyExpences(user.getUid());
		return total;
	}
	
	@GetMapping("yearlyExpences")
	public String yearlyExpences()
	{
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String email = authentication.getName();  

	       
	        users user = dao.getUserByEmail(email);
		String total=totalExpencesService.yearlyExpences(user.getUid());
		return total;
	}
	
	
	@GetMapping("dailyFoodExpences")
	public String dailyFoodExpences()
	{
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String email = authentication.getName();  

	       
	        users user = dao.getUserByEmail(email);
		String total=totalExpencesService.dailyFoodExpences(user.getUid());
		return total;
	}
	
	@GetMapping("dailyTravelExpences")
	public String dailyTravelExpences()
	{
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String email = authentication.getName();  

	       
	        users user = dao.getUserByEmail(email);
		String total=totalExpencesService.dailyTravelExpences(user.getUid());
		return total;
	}

	@GetMapping("dailyShoppingExpences")
	public String dailyShoppingExpences()
	{
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String email = authentication.getName();  

	       
	        users user = dao.getUserByEmail(email);
		String total=totalExpencesService.dailyShoppingExpences(user.getUid());
		return total;
	}

	@GetMapping("dailyOthersExpences")
	public String dailyOthersExpences()
	{
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String email = authentication.getName();  

	       
	        users user = dao.getUserByEmail(email);
		String total=totalExpencesService.dailyOthersExpences(user.getUid());
		return total;
	}
	
	@PostMapping("monthlyCategoryExpenses")
	public String monthlyCategoryExpenses(@RequestBody MonthRequest request)
	{
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String email = authentication.getName();  

	       
	        users user = dao.getUserByEmail(email);
		String total=totalExpencesService.monthlyCategoryExpenses(user.getUid(),request.getYyyy(),request.getMm(),request.getCategory());
		return total;
	}

	@PostMapping("yearlyCategoryExpenses")
	public String yearlyCategoryExpenses(@RequestBody MonthRequest request)
	{
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String email = authentication.getName();  

	       
	        users user = dao.getUserByEmail(email);
		String total=totalExpencesService.yearlyCategoryExpenses(user.getUid(),request.getYyyy(),request.getCategory());
		return total;
	}

}
