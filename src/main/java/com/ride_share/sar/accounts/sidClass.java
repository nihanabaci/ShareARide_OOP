package com.ride_share.sar.accounts;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class sidClass {
	long sid;
	
	public sidClass()
	{
		
	}

	public sidClass(long sid) {
		super();
		this.sid = sid;
	}

	public long getSid() {
		return sid;
	}

	public void setSid(long sid) {
		this.sid = sid;
	}
	

}

