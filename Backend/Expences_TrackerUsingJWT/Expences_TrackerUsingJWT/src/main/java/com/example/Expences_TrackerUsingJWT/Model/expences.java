package com.example.Expences_TrackerUsingJWT.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="expences")
public class expences {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eid;
	private String title;
	private int amount;
	private String edate;
	private int uid;
	private int cid;
	
	
	public expences(String title, int amount, String edate, int uid, int cid) {
		super();
		this.title = title;
		this.amount = amount;
		this.edate = edate;
		this.uid = uid;
		this.cid = cid;
	}
	
	public expences(String edate, String title, Integer amount) {
        this.edate = edate;
        this.title = title;
        this.amount = amount;
    }
	
	public expences() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getEid() {
		return eid;
	}
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	@Override
	public String toString() {
		return "expences [eid=" + eid + ", title=" + title + ", amount=" + amount + ", edate=" + edate + ", uid=" + uid
				+ ", cid=" + cid + "]";
	}


	
	
	


}
