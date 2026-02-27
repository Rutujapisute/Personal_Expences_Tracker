package com.example.Expences_TrackerUsingJWT.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class categories {

	private int cid;
	private String cname;
	
	@Id
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "categories [cid=" + cid + ", cname=" + cname + "]";
	}
	
	
	
}
