package com.example.Expences_TrackerUsingJWT.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Expences_TrackerUsingJWT.Dao.Dao;
import com.example.Expences_TrackerUsingJWT.Model.users;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private Dao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        users user1 = dao.getUserByEmail(username);

        if (user1 == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return User.withUsername(user1.getEmail())
                .password(user1.getPassword())
                .roles("USER")
                .build();    
        }
}
