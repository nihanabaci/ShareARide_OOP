package com.ride_share.sar.rides;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DateTime {
	String date;
	String time;
	
	public DateTime()
	{
		
	}
	public DateTime(String d, String t)
	{
		date =d;
		time = t;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	

}
