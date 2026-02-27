package com.example.Expences_TrackerUsingJWT.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Expences_TrackerUsingJWT.Dao.Dao;

@Service
public class User {

	@Autowired
	private Dao dao;
	
	
public String deleteUserById(int uid)
{
return dao.deleteUserById(uid);
}
}
