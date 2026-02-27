package com.example.Expences_TrackerUsingJWT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Expences_TrackerUsingJWT.Dao.Dao;
import com.example.Expences_TrackerUsingJWT.Helper.JwtUtil;
import com.example.Expences_TrackerUsingJWT.Model.JwtResponse;
import com.example.Expences_TrackerUsingJWT.Model.users;
import com.example.Expences_TrackerUsingJWT.Services.CustomUserDetailsService;

@RestController
public class Home {
	
	 @Autowired
	    private Dao dao;

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    @Autowired
	    private JwtUtil jwtUtil;  // 👈 Add this for token generation

	    @Autowired
	    private CustomUserDetailsService customUserDetailsService;  // 👈 Add this

	    @PostMapping("/register")  // 👈 Spelling fix: ragister -> register
	    public ResponseEntity<String> registerUser(@RequestBody users newUser) {
	        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
	        dao.ragister(newUser);
	        return ResponseEntity.ok("Registration successful");
	    }
	    
	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody users usr) {
	        users user = dao.login(usr.getEmail());  // 👈 Only email pass
	        
	        if (user == null || !passwordEncoder.matches(usr.getPassword(), user.getPassword())) {  // 👈 Password check here
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
	        }
	        
	        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());
	        String token = jwtUtil.generateToken(userDetails);
	        
	        return ResponseEntity.ok(new JwtResponse(token));
	    }

	    @RequestMapping("/welcome")
	    public String welcome() {
	        return "Welcome to your page";
	    }
	    





}
