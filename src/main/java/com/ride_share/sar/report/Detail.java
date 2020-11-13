package com.ride_share.sar.report;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Detail {
	String from_zip;
	String to_zip;
	int count;
	
	public Detail()
	{
		
	}
	public Detail(String from_zip, String to_zip, int count) {
		super();
		this.from_zip = from_zip;
		this.to_zip = to_zip;
		this.count = count;
	}
	public String getFrom_zip() {
		return from_zip;
	}
	public void setFrom_zip(String from_zip) {
		this.from_zip = from_zip;
	}
	public String getTo_zip() {
		return to_zip;
	}
	public void setTo_zip(String to_zip) {
		this.to_zip = to_zip;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	

}
