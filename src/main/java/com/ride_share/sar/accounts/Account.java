package com.ride_share.sar.accounts;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Account {
	Boolean is_active;
	long aid;
	String first_name;
	String last_name;
	String phone;
	String picture;
	
	
	public Account()
	{
		
	}
	public Account(Boolean act, long id, String f, String l, String cp, String p)
	{
		this.is_active = act;
		this.aid = id;
		this.first_name = f;
		this.last_name = l;
		this.phone = cp;
		this.picture = p;
	
	}
	public  Boolean getIs_active() {
		return is_active;
	}
	public  void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}
	public long getAid() {
		return aid;
	}
	public void setAid(long aid) {
		this.aid = aid;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

}
