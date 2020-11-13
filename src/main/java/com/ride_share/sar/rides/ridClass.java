package com.ride_share.sar.rides;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ridClass {
long rid;
	
	public ridClass()
	{
		
	}

	public ridClass(long rid) {
		super();
		this.rid = rid;
	}

	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
		this.rid = rid;
	}
	

}
