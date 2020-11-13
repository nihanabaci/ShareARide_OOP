package com.ride_share.sar.accounts;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class idClass {
	long aid;
	
	public idClass()
	{
		
	}

	public idClass(long aid) {
		super();
		this.aid = aid;
	}

	public long getAid() {
		return aid;
	}

	public void setAid(long aid) {
		this.aid = aid;
	}
	

}
