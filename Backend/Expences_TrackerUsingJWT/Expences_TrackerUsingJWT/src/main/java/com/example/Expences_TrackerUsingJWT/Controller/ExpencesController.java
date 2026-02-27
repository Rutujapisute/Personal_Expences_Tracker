package com.example.Expences_TrackerUsingJWT.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.Expences_TrackerUsingJWT.Model.MonthRequest;
import com.example.Expences_TrackerUsingJWT.Model.expences;
import com.example.Expences_TrackerUsingJWT.Model.users;
import com.example.Expences_TrackerUsingJWT.Services.ExpencesService;
import com.example.Expences_TrackerUsingJWT.Dao.Dao;  // Dao import करा

@RestController
public class ExpencesController 
{
    @Autowired
    private ExpencesService expencesService;

    @Autowired
    private Dao dao;  // Dao inject करा, कारण user चे uid काढण्यासाठी लागेल

    @GetMapping("getExpences")
    public List<expences> getExpences()
	{
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();  

       
        users user = dao.getUserByEmail(email);
		 List<expences> list=expencesService.getExpences(user.getUid())	;
		 return list;
	}
    
    @GetMapping("getDailyExpences")
    List<expences> getDailyExpences()
    {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();  

       
        users user = dao.getUserByEmail(email);
        List<expences> list = expencesService.getDailyExpences(user.getUid());
        return list;
    }
    
    @GetMapping("getMonthlyExpences")
    List<expences> getMonthlyExpences()
    {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();  

       
        users user = dao.getUserByEmail(email);
        List<expences> list = expencesService.getMonthlyExpences(user.getUid());
        return list;
    }
    @GetMapping("getYearlyExpences")
    List<expences> getYearlyExpences()
    {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();  

       
        users user = dao.getUserByEmail(email);
        List<expences> list = expencesService.getYearlyExpences(user.getUid());
        return list;
    }
    
    @PostMapping("addExpences")
    public String addExpences(@RequestBody expences exp) {
        // Step 1: Current user ची authentication काढा
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();  

       
        users user = dao.getUserByEmail(email);
        if (user != null) {
            exp.setUid(user.getUid());  // uid सेट करा
        } else {
            return "User not found";  // Error handle करा
        }

        // Step 3: Service मध्ये call करा
        return expencesService.addExpences(exp);
    }
    
    @PostMapping("getMonthlyExpensesForSecondMenu")
   public  List<expences> getMonthlyExpensesForSecondMenu(@RequestBody MonthRequest request)
   {
	   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String email = authentication.getName();  

      
       users user = dao.getUserByEmail(email);
		 List<expences> list=expencesService.getMonthlyExpensesForSecondMenu(user.getUid(),request.getYyyy(),request.getMm())	;
		return list;
   }
   
   @PostMapping("getYearlyExpensesList")
   public  List<expences> getYearlyExpensesList(@RequestBody MonthRequest request)
   {
	   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String email = authentication.getName();  

      
       users user = dao.getUserByEmail(email);
		 List<expences> list=expencesService.getYearlyExpensesList(user.getUid(),request.getYyyy())	;
		return list;
   }
}