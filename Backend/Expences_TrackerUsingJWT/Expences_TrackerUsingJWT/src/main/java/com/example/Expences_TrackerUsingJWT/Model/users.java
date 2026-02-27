package com.example.Expences_TrackerUsingJWT.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")

public class users {
	 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uid;
	private String uname;
	private String email;
	private String password;
	
	
	public int getUid() {
		return uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "users [uid=" + uid + ", uname=" + uname + ", email=" + email + ", password=" + password + "]";
	}
	
	

}
