package com.ride_share.sar.rides;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class jidClass {
long jid;
	
	public jidClass()
	{
		
	}

	public jidClass(long jid) {
		super();
		this.jid = jid;
	}

	public long getJid() {
		return jid;
	}

	public void setJid(long jid) {
		this.jid = jid;
	}
	

}
