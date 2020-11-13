package com.ride_share.sar.rides;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class midClass {
long mid;
	
	public midClass()
	{
		
	}

	public midClass(long mid) {
		super();
		this.mid = mid;
	}

	public long getMid() {
		return mid;
	}

	public void setMid(long mid) {
		this.mid = mid;
	}
	

}
