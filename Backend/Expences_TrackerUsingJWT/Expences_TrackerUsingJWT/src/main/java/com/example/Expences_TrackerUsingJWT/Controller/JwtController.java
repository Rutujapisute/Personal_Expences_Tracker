package com.example.Expences_TrackerUsingJWT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Expences_TrackerUsingJWT.Dao.Dao;
import com.example.Expences_TrackerUsingJWT.Helper.JwtUtil;
import com.example.Expences_TrackerUsingJWT.Model.JwtRequest;
import com.example.Expences_TrackerUsingJWT.Model.JwtResponse;
import com.example.Expences_TrackerUsingJWT.Model.users;
import com.example.Expences_TrackerUsingJWT.Services.CustomUserDetailsService;

@RestController
public class JwtController {

	  @Autowired
	    private JwtUtil jwtUtil;

	    @Autowired
	    private CustomUserDetailsService customUserDetailsService;

	    @Autowired
	    private PasswordEncoder passwordEncoder;  // 👈 Add

	    @Autowired
	    private Dao dao;  // 👈 Add

	    @PostMapping("/token")
	    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
	        users user = dao.login(jwtRequest.getUsername());  // 👈 Only email pass करा (password काढा)

	        if (user == null || !passwordEncoder.matches(jwtRequest.getPassword(), user.getPassword())) {  // 👈 Password check येथे करा
	            throw new Exception("Bad credentials");
	        }

	        UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
	        String token = jwtUtil.generateToken(userDetails);

	        return ResponseEntity.ok(new JwtResponse(token));
	    }
	    }
