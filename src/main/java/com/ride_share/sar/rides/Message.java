package com.ride_share.sar.rides;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
	long aid;
	String msg;
	long mid = 0;
	long rid;
	
	public Message()
	{
		
	}
	Message(long ri, long ai, String m, long mi)
	{
		rid = ri;
		aid = ai;
		msg = m;
		mid = mi;
	}
	public long getRid() {
		return rid;
	}
	public void setRid(long rid) {
		this.rid = rid;
	}
	public long getAid() {
		return aid;
	}
	public void setAid(long aid) {
		this.aid = aid;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public long getMid() {
		return mid;
	}
	public void setMid(long mid) {
		this.mid = mid;
	}
	
}
